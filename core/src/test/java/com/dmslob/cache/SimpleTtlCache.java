package com.dmslob.cache;

import java.util.concurrent.ConcurrentHashMap;

public class SimpleTtlCache<K, V> {
    private final ConcurrentHashMap<K, CacheItem<V>> cache = new ConcurrentHashMap<>();
    private final long defaultTtlMillis;

    public SimpleTtlCache(long defaultTtlMillis) {
        this.defaultTtlMillis = defaultTtlMillis;
    }

    public void put(K key, V value) {
        long expiry = System.currentTimeMillis() + defaultTtlMillis;
        cache.put(key, new CacheItem<>(value, expiry));
    }

    /** Put with custom TTL */
    public void put(K key, V value, long ttlMillis) {
        long expiry = System.currentTimeMillis() + ttlMillis;
        cache.put(key, new CacheItem<>(value, expiry));
    }

    public V get(K key) {
        CacheItem<V> item = cache.get(key);
        if (item == null) {
            return null;
        }
        if (item.expiryTime() < System.currentTimeMillis()) {
            cache.remove(key); // lazy cleanup
            return null;
        }
        return item.value();
    }

    public int size() {
        return cache.size();
    }

    private record CacheItem<V>(V value, long expiryTime) {}
}
