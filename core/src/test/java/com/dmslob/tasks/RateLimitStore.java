package com.dmslob.tasks;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class RateLimitStore {
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<Long>> cache = new ConcurrentHashMap<>();
    // Limit the number of keys in the cache
    private final int maxCacheSize;

    public RateLimitStore(int maxCacheSize) {
        this.maxCacheSize = maxCacheSize;
    }

    public boolean tryAcquire(String key, int maxRequests, long windowMs) {
        long now = System.currentTimeMillis();
        if (cache.size() > maxCacheSize) {
            cleanup();
        }
        ConcurrentLinkedQueue<Long> timestamps =
                cache.computeIfAbsent(key, k -> new ConcurrentLinkedQueue<>());
        synchronized (timestamps) {
            // Remove expired timestamps
            while (!timestamps.isEmpty() && now - timestamps.peek() > windowMs) {
                timestamps.poll();
            }
            if (timestamps.size() >= maxRequests) {
                return false;
            }
            timestamps.add(now);
            return true;
        }
    }

    private void cleanup() {
        cache.entrySet()
                .removeIf(entry -> entry.getValue().isEmpty());
    }
}
