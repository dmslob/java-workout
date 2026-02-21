package com.dmslob.tasks.ratelimiter;

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
        var timestampQueue = cache.computeIfAbsent(key, k -> new ConcurrentLinkedQueue<>());
        synchronized (timestampQueue) {
            // Remove expired timestamps
            while (!timestampQueue.isEmpty() && now - timestampQueue.peek() > windowMs) {
                timestampQueue.poll();
            }
            if (timestampQueue.size() >= maxRequests) {
                return false;
            }
            timestampQueue.add(now);
            return true;
        }
    }

    private void cleanup() {
        cache.entrySet()
                .removeIf(entry -> entry.getValue().isEmpty());
    }
}
