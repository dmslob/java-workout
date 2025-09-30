package com.dmslob.cache;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * In-memory “single-flight” layer that:
 * — Performs exactly ONE load for the same key, with the rest waiting for the same result.
 * — Has a short-lived cache (TTL) to protect against cache stampedes.
 * — Is thread-safe and runs in O(1) per call (without full passes).
 * — Transparent exception throwing to all competing waiters if the loader crashes.
 * Conditions
 * — API: CompletableFuture<V> getOrLoad(K key, Supplier<V> loader) (or Supplier<CompletableFuture<V>>
 * if the load is already async).
 * — If there is an unburned key in the cache, return it immediately.
 * If not, start a single load for the key and distribute the same Future to all concurrent calls.
 * — When the load is complete, put the result in the cache with expireAt.
 * — Deletion of expired entries is lazy (on read/write), without background scanners.
 * — Critical sections are minimal; no global locks.
 * <p>
 * A small single-flight + TTL cache implementation.
 * - Concurrent; O(1) ops using ConcurrentHashMap.
 * - Lazy expiration (on read/write).
 * - Single in-flight load per key; all concurrent callers receive the same CompletableFuture.
 * - Exceptions from loader are propagated to all waiters.
 */
public class SingleFlightCache<K, V> {

    private final ConcurrentHashMap<K, CacheEntry<V>> cache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<K, CompletableFuture<V>> inflight = new ConcurrentHashMap<>();
    // Optional executor for wrapping synchronous loaders without blocking callers if desired.
    // If null, synchronous loader runs in caller thread.
    private final Executor loaderExecutor;

    public SingleFlightCache() {
        this.loaderExecutor = null;
    }

    /**
     * Use this constructor if you want sync loaders offloaded to an executor to avoid blocking callers.
     */
    public SingleFlightCache(Executor loaderExecutor) {
        this.loaderExecutor = loaderExecutor;
    }

    /**
     * Synchronous loader variant.
     * <p>
     * If there is a cached, unexpired value -> returned immediately (completed future).
     * Otherwise, starts (or joins) a single load for the key and returns its future.
     */
    public CompletableFuture<V> getOrLoad(K key, Supplier<V> loader, Duration ttl) {
        Objects.requireNonNull(loader, "loader");
        // Wrap sync loader in a Supplier<CompletableFuture<V>>
        Supplier<CompletableFuture<V>> asyncWrapper = () -> {
            if (loaderExecutor != null) {
                // run loader on provided executor to avoid blocking caller
                return CompletableFuture.supplyAsync(loader, loaderExecutor);
            } else {
                // run directly in caller thread (blocking)
                CompletableFuture<V> p = new CompletableFuture<>();
                try {
                    V v = loader.get();
                    p.complete(v);
                } catch (Throwable t) {
                    p.completeExceptionally(t);
                }
                return p;
            }
        };
        return getOrLoadAsync(key, asyncWrapper, ttl);
    }

    /**
     * Async loader variant: loader returns a CompletableFuture.
     */
    public CompletableFuture<V> getOrLoadAsync(K key, Supplier<CompletableFuture<V>> asyncLoader, Duration ttl) {
        Objects.requireNonNull(asyncLoader, "asyncLoader");
        long now = System.nanoTime();
        // 1) Fast path: cached, valid entry
        CacheEntry<V> entry = cache.get(key);
        if (entry != null) {
            if (!entry.isExpiredAt(now)) {
                return CompletableFuture.completedFuture(entry.value);
            } else {
                // lazy removal (best-effort)
                cache.remove(key, entry);
            }
        }
        // 2) Single-flight: avoid duplicate loads
        // Try reuse existing in-flight future
        CompletableFuture<V> existing = inflight.get(key);
        if (existing != null) {
            return existing;
        }
        // Create a new promise and attempt to put it as 'inflight' atomically
        CompletableFuture<V> promise = new CompletableFuture<>();
        CompletableFuture<V> prev = inflight.putIfAbsent(key, promise);
        if (prev != null) {
            // someone else won the race
            return prev;
        }
        // We are responsible for starting the load and completing the promise.
        CompletableFuture<V> loaderFuture;
        try {
            loaderFuture = asyncLoader.get();
            if (loaderFuture == null) {
                throw new NullPointerException("asyncLoader returned null future");
            }
        } catch (Throwable t) {
            // loader threw synchronously; clean inflight and complete exceptionally
            inflight.remove(key, promise);
            promise.completeExceptionally(t);
            return promise;
        }
        // When loaderFuture completes, we must:
        //  - on success: put value into cache with TTL
        //  - on any completion: remove inflight for the key (only if still mapped to this promise)
        long ttlNanos = ttl == null ? 0L : ttl.toNanos();
        loaderFuture.whenComplete((value, err) -> {
            try {
                if (err == null) {
                    if (ttlNanos > 0) {
                        long expireAt = System.nanoTime() + ttlNanos;
                        cache.put(key, new CacheEntry<>(value, expireAt));
                    }
                    promise.complete(value);
                } else {
                    promise.completeExceptionally(err);
                }
            } finally {
                // remove inflight only if it still maps to this promise
                inflight.remove(key, promise);
            }
        });

        return promise;
    }

    /**
     * Manual invalidation (optional).
     */
    public void invalidate(K key) {
        cache.remove(key);
    }

    /**
     * Return cached value or null if absent or expired.
     */
    public V peek(K key) {
        CacheEntry<V> e = cache.get(key);
        if (e == null) return null;
        if (e.isExpiredAt(System.nanoTime())) {
            cache.remove(key, e);
            return null;
        }
        return e.value;
    }

    private static final class CacheEntry<V> {
        final V value;
        // expireAt in System.nanoTime() units; 0 means no caching
        final long expireAtNanos;

        CacheEntry(V value, long expireAtNanos) {
            this.value = value;
            this.expireAtNanos = expireAtNanos;
        }

        boolean isExpiredAt(long nowNano) {
            return expireAtNanos != 0L && nowNano >= expireAtNanos;
        }
    }

    // ---------- Example / simple concurrency test ----------
    public static void main(String[] args) throws Exception {
        SingleFlightCache<String, String> sf = new SingleFlightCache<>(Executors.newCachedThreadPool());
        AtomicInteger loaderCalls = new AtomicInteger();

        Supplier<CompletableFuture<String>> loader = () -> CompletableFuture.supplyAsync(() -> {
            int n = loaderCalls.incrementAndGet();
            try {
                // simulate IO / latency
                Thread.sleep(200);
            } catch (InterruptedException ignored) {
            }
            return "value-" + n;
        });

        Duration ttl = Duration.ofMillis(1000);

        // launch many concurrent callers for same key
        int threads = 20;
        ExecutorService exec = Executors.newFixedThreadPool(threads);
        CompletableFuture<?>[] futures = new CompletableFuture<?>[threads];
        for (int i = 0; i < threads; i++) {
            futures[i] = CompletableFuture.runAsync(() -> {
                CompletableFuture<String> f = sf.getOrLoadAsync("k1", loader, ttl);
                try {
                    String v = f.get(); // wait for same result
                    System.out.println(Thread.currentThread().getName() + " got: " + v);
                } catch (Throwable ex) {
                    ex.printStackTrace();
                }
            }, exec);
        }
        CompletableFuture.allOf(futures).join();
        System.out.println("loader invoked: " + loaderCalls.get() + " time(s)"); // should be 1

        // Wait TTL expiry, then next call should cause another load
        Thread.sleep(1200);
        String v2 = sf.getOrLoadAsync("k1", loader, ttl).get();
        System.out.println("after ttl, got: " + v2);
        System.out.println("loader invoked total: " + loaderCalls.get()); // should be 2
        exec.shutdown();
        System.exit(0);
    }
}
