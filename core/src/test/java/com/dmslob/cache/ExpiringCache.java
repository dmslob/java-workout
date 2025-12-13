package com.dmslob.cache;

import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class ExpiringCache<K, V> {
    private final ConcurrentMap<K, CacheEntry<V>> cache;
    private final Queue<K> evictionQueue;
    private final int maxSize;
    private final AtomicLong hits;
    private final AtomicLong misses;

    public ExpiringCache(int maxSize) {
        this.cache = new ConcurrentHashMap<>();
        this.evictionQueue = new ConcurrentLinkedQueue<>();
        this.maxSize = maxSize;
        this.hits = new AtomicLong(0);
        this.misses = new AtomicLong(0);
    }

    public void put(K key, V value, long ttlInMillis) {
        long expirationTime = System.currentTimeMillis() + ttlInMillis;
        CacheEntry<V> entry = new CacheEntry<>(value, expirationTime);
        if (cache.size() >= maxSize) {
            evict();
        }
        cache.put(key, entry);
        evictionQueue.add(key);
    }

    public Optional<V> get(K key) {
        CacheEntry<V> entry = cache.get(key);
        if (entry == null || entry.isExpired()) {
            misses.incrementAndGet();
            cache.remove(key);
            return Optional.empty();
        }
        hits.incrementAndGet();
        return Optional.of(entry.value());
    }

    public void remove(K key) {
        cache.remove(key);
        evictionQueue.remove(key);
    }

    public int size() {
        return (int) cache.values().stream().filter(entry -> !entry.isExpired()).count();
    }

    public long getHits() {
        return hits.get();
    }

    public long getMisses() {
        return misses.get();
    }

    public void shutdown() {
        cache.clear();
        evictionQueue.clear();
    }

    private void evict() {
        while (evictionQueue.size() > maxSize) {
            K oldestKey = evictionQueue.poll();
            if (oldestKey != null) {
                cache.remove(oldestKey);
            }
        }
    }

    private record CacheEntry<V>(V value, long expirationTime) {
        public boolean isExpired() {
            return System.currentTimeMillis() > expirationTime;
        }
    }
}