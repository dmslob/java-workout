package com.dmslob.cache;

import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * A cache stampede, also known as the "thundering herd problem" or "dog pile effect,"
 * occurs when a cached item expires or is invalidated, and a large number of concurrent requests
 * simultaneously attempt to re-fetch or re-compute that same item from the underlying data source
 * (e.g., database, external API). This surge of requests can overwhelm the data source,
 * leading to performance degradation, increased latency, and potential system instability or outages.
 * The Solution
 * We’ll combine:
 * Per-key lock or "single-flight" control — ensures only one thread loads the data for a key at a time.
 * Background refresh with soft-expiration — optionally, allow serving slightly stale data while
 * refreshing in the background.
 * TTL (Time-To-Live) to expire entries.
 */
public class CacheStampedeTest {

    static class StampedeSafeCache<K, V> {
        private final ConcurrentHashMap<K, CacheValue<V>> cache = new ConcurrentHashMap<>();
        private final ConcurrentHashMap<K, CompletableFuture<V>> singleFlight = new ConcurrentHashMap<>();
        private final Duration ttl;

        public StampedeSafeCache(Duration ttl) {
            this.ttl = ttl;
        }

        private record CacheValue<V>(V value, Instant expiryTime) {
            boolean isExpired() {
                return Instant.now().isAfter(expiryTime);
            }
        }

        public CompletableFuture<V> getOrLoad(K key, Supplier<V> loader) {
            // Step 1: check existing cached value
            CacheValue<V> existing = cache.get(key);
            if (existing != null && !existing.isExpired()) {
                return CompletableFuture.completedFuture(existing.value);
            }
            // Step 2: check if a load is already in progress
            return singleFlight.computeIfAbsent(key, k ->
                    CompletableFuture.supplyAsync(() -> {
                        try {
                            V loadedValue = loader.get();
                            cache.put(k, new CacheValue<>(loadedValue, Instant.now().plus(ttl)));
                            return loadedValue;
                        } finally {
                            singleFlight.remove(k);
                        }
                    })
            );
        }

        public void invalidate(K key) {
            cache.remove(key);
        }

        public void clear() {
            cache.clear();
        }
    }

    private final StampedeSafeCache<String, String> cache = new StampedeSafeCache<>(Duration.ofSeconds(5));
    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    @Test
    public void should_test_StampedeSafeCache() throws InterruptedException {
        // given
        String key = "key";
        Runnable task = () -> cache.getOrLoad(key, () -> {
            System.out.printf("%s is loading...%n", Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ignored) {
            }
            return "LoadedValue@%s".formatted(System.currentTimeMillis());
        }).thenAccept(value -> System.out.printf("%s got %s%n", Thread.currentThread().getName(), value));
        // when
        // Simulate multiple concurrent requests for same key
        for (int i = 0; i < 5; i++) {
            executor.submit(task);
        }
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(5);
    }
}
