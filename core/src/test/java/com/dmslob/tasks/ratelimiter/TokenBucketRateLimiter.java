package com.dmslob.tasks.ratelimiter;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicLong;

/**
 - The token bucket algorithm allocates tokens at a fixed rate into a "bucket."
 - Each request consumes a token from the bucket, and requests are only
 allowed if there are enough tokens available.
 - Unused tokens are stored in the bucket, up to a maximum capacity.
 - This algorithm provides a simple and flexible way to control the rate
 of requests and smooth out bursts of traffic
 */
@Slf4j
public class TokenBucketRateLimiter {
    private final long capacity;
    private final long refillTokensPerSecond;
    // Current tokens (stored as scaled value for precision)
    private final AtomicLong availableTokens;
    // Last refill timestamp in nanos
    private final AtomicLong lastRefillTime;

    public TokenBucketRateLimiter(long capacity, long refillTokensPerSecond) {
        if (capacity <= 0 || refillTokensPerSecond <= 0) {
            throw new IllegalArgumentException("Capacity and refill rate must be > 0");
        }
        this.capacity = capacity;
        this.refillTokensPerSecond = refillTokensPerSecond;
        this.availableTokens = new AtomicLong(capacity);
        this.lastRefillTime = new AtomicLong(System.nanoTime());
    }

    public boolean tryConsume() {
        refill();
        while (true) {
            long currentTokens = availableTokens.get();
            if (currentTokens <= 0) {
                return false;
            }
            if (availableTokens.compareAndSet(currentTokens, currentTokens - 1)) {
                log.info("tryConsume {} tokens", currentTokens);
                return true;
            }
        }
    }

    private void refill() {
        long now = System.nanoTime();
        long lastRefill = lastRefillTime.get();
        long elapsedNanos = now - lastRefill;
        if (elapsedNanos <= 0) {
            return;
        }
        long tokensToAdd = (elapsedNanos * refillTokensPerSecond) / 1_000_000_000L;
        if (tokensToAdd > 0) {
            long newLastRefillTime = lastRefill +
                    (tokensToAdd * 1_000_000_000L) / refillTokensPerSecond;
            if (lastRefillTime.compareAndSet(lastRefill, newLastRefillTime)) {
                availableTokens.updateAndGet(current ->
                        Math.min(capacity, current + tokensToAdd)
                );
            }
        }
    }

    public long getAvailableTokens() {
        refill();
        return availableTokens.get();
    }
}