package com.dmslob.tasks.ratelimiter;

import java.util.concurrent.ConcurrentHashMap;

public class FixedWindowRateLimiterPerKey {

    private final int maxRequests;
    private final long windowSizeMillis;

    private final ConcurrentHashMap<String, FixedWindowRateLimiter> limiters = new ConcurrentHashMap<>();

    public FixedWindowRateLimiterPerKey(int maxRequests, long windowSizeMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeMillis = windowSizeMillis;
    }

    public boolean tryConsume(String key) {
        return limiters
                .computeIfAbsent(key,
                        k -> new FixedWindowRateLimiter(maxRequests, windowSizeMillis))
                .tryConsume();
    }
}