package com.dmslob.tasks;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Debouncer that delays execution of an action while events continue to arrive
 */
public class Debouncer {
    private final ScheduledExecutorService scheduler;
    private final long delayMs;
    // AtomicReference holds the current "active" task to ensure thread safety
    private final AtomicReference<ScheduledFuture<?>> futureRef = new AtomicReference<>();

    public Debouncer(int threadPoolSize, long delayMs) {
        this.scheduler = Executors.newScheduledThreadPool(threadPoolSize);
        this.delayMs = delayMs;
    }

    public void call(Runnable action) {
        ScheduledFuture<?> previous = futureRef.getAndSet(
                scheduler.schedule(() -> {
                    try {
                        action.run();
                    } finally {
                        // Optional: clear the reference once the action completes
                        futureRef.compareAndSet(futureRef.get(), null);
                    }
                }, delayMs, TimeUnit.MILLISECONDS)
        );
        // Cancel the previous task if it exists
        if (previous != null) {
            previous.cancel(false); // false: don't interrupt if already running
        }
    }

    public void shutdown() {
        scheduler.shutdownNow();
    }
}