package com.dmslob.tasks.ratelimiter;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * - The fixed window counting algorithm tracks the number of requests within a
 * fixed time window (e.g., one minute, one hour).
 * - Requests exceeding a predefined threshold within the window
 * are rejected or delayed until the window resets.
 * - This algorithm provides a straightforward way to limit the rate of requests over short periods, but it may not handle bursts of traffic well.
 */
public class FixedWindowRateLimiter {

    private final int maxRequests;
    private final long windowSizeNanos;

    private final AtomicInteger requestCount = new AtomicInteger(0);
    private final AtomicLong windowStart = new AtomicLong(System.nanoTime());

    public FixedWindowRateLimiter(int maxRequests, long windowSizeMillis) {
        if (maxRequests <= 0 || windowSizeMillis <= 0) {
            throw new IllegalArgumentException("maxRequests and windowSize must be > 0");
        }

        this.maxRequests = maxRequests;
        this.windowSizeNanos = windowSizeMillis * 1_000_000L;
    }

    public boolean tryConsume() {
        long now = System.nanoTime();
        long currentWindowStart = windowStart.get();
        if (now - currentWindowStart >= windowSizeNanos) {
            resetWindow(now);
        }
        while (true) {
            int currentCount = requestCount.get();
            if (currentCount >= maxRequests) {
                return false;
            }
            if (requestCount.compareAndSet(currentCount, currentCount + 1)) {
                return true;
            }
        }
    }

    private void resetWindow(long now) {
        long currentWindowStart = windowStart.get();
        if (now - currentWindowStart >= windowSizeNanos) {
            if (windowStart.compareAndSet(currentWindowStart, now)) {
                requestCount.set(0);
            }
        }
    }

    public int getCurrentCount() {
        return requestCount.get();
    }
}