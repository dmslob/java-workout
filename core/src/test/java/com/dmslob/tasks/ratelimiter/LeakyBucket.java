package com.dmslob.tasks.ratelimiter;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * - The leaky bucket algorithm models a bucket with a leaky hole,
 * where requests are added at a constant rate and leak out at a controlled rate.
 * - Incoming requests are added to the bucket, and if the bucket exceeds
 * a certain capacity, excess requests are either delayed or rejected.
 * - This algorithm provides a way to enforce a maximum request rate while
 * allowing some burstiness in traffic.
 */
@Slf4j
public class LeakyBucket {
    private final BlockingQueue<Integer> bucket;
    private final ScheduledExecutorService leakScheduler;
    /**
     * @param capacity Maximum number of requests the bucket can hold.
     * @param leakRate Requests processed per second.
     */
    public LeakyBucket(int capacity, int leakRate) {
        this.bucket = new ArrayBlockingQueue<>(capacity);
        this.leakScheduler = Executors.newScheduledThreadPool(1);
        // Calculate the interval between leaks in nanoseconds
        // e.g., if leakRate is 2/sec, the interval is 0.5 seconds
        long leakInterval = 1_000_000_000 / leakRate;
        // Schedule the leak task to run at a fixed rate
        leakScheduler.scheduleAtFixedRate(this::leak, 0, leakInterval, TimeUnit.NANOSECONDS);
    }

    /**
     * Adds a request to the bucket.
     *
     * @param requestId An ID or data representing the request.
     * @return true if added (accepted), false if full (rejected).
     */
    public boolean addRequest(int requestId) {
        // offer() returns false immediately if the queue is full
        if (bucket.offer(requestId)) {
            log.info("Request {} added to bucket (Queued)", requestId);
            return true;
        } else {
            log.warn("Request {} : Bucket Full (Queue Full) -> DROPPED", requestId);
            return false;
        }
    }

    /**
     * The "Leak" process. Removes one item from the queue and processes it.
     */
    private void leak() {
        if (!bucket.isEmpty()) {
            Integer requestId = bucket.poll();
            processRequest(requestId);
        }
    }

    private void processRequest(Integer requestId) {
        log.info("Leaked (Processed) Request {} at {}", requestId, System.currentTimeMillis());
    }

    public void shutdown() {
        leakScheduler.shutdownNow();
    }
}