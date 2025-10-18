package com.dmslob.tasks.concurrency;

import org.testng.annotations.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * We need to start exactly 5 threads, each calls doWork(),
 * and wait for all of them to finish
 * before continuing with the main thread.
 */
public class ThreadsExecutionTest {
    // Number of threads to execute the task
    private static final int NUM_THREADS = 5;
    // Timeout for waiting for threads to finish
    private static final long TIMEOUT_SECONDS = 10;
    /**
     * Simulates a heavy operation.
     */
    void doWork(int threadId) {
        // Simulate file reading/processing or a heavy computation
        long startTime = System.currentTimeMillis();
        System.out.printf("Thread %s started doWork()...%n", threadId);
        try {
            // Simulate work taking a random amount of time (e.g., 1-3 seconds)
            long duration = (long) (Math.random() * 2000) + 1000;
            TimeUnit.MILLISECONDS.sleep(duration);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
            System.err.printf("Thread %s was interrupted.%n", threadId);
        }
        long endTime = System.currentTimeMillis();
        System.out.printf("Thread %s finished doWork() in %s ms.%n", threadId, (endTime - startTime));
    }

    @Test
    public void should_run_threads_with_executor() {
        try (ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS)) {
            System.out.printf("Starting %s worker threads...%n", NUM_THREADS);
            for (int i = 0; i < NUM_THREADS; i++) {
                final int threadId = i + 1;
                executor.submit(() -> doWork(threadId));
            }
            // This stops the executor from accepting new tasks but lets existing ones finish.
            executor.shutdown();
            System.out.println("\nMain thread is waiting for worker threads to complete...");
            try {
                // Block until all tasks have completed execution after a shutdown request,
                // or the timeout occurs, or the current thread is interrupted.
                boolean terminated = executor.awaitTermination(TIMEOUT_SECONDS, TimeUnit.SECONDS);
                if (terminated) {
                    System.out.println("All worker threads finished successfully.");
                } else {
                    System.out.println("Timed out before all worker threads finished. Some tasks may be incomplete.");
                    // Optionally, force a shutdown of any remaining tasks
                    // executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                System.err.println("\nMain thread was interrupted while waiting.");
                executor.shutdownNow();
                Thread.currentThread().interrupt(); // Restore the interrupted status
            }
        }
        System.out.println("Main thread continues execution.");
    }

    @Test
    public void should_run_threads_with_CountDownLatch() {
        final CountDownLatch latch = new CountDownLatch(NUM_THREADS);
        try (ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS)) {
            System.out.printf("Starting %s worker threads...%n", NUM_THREADS);
            for (int i = 0; i < NUM_THREADS; i++) {
                final int threadId = i + 1;
                executor.submit(() -> {
                    doWork(threadId);
                    latch.countDown();
                });
            }
            System.out.printf("\nMain thread is waiting for " +
                    "worker threads to complete (latch count: %s)...", latch.getCount());
            try {
                // Blocks the main thread until the latch count reaches zero
                latch.await();
                System.out.println("\nAll worker threads finished (latch count is 0).");
            } catch (InterruptedException e) {
                System.err.println("Main thread was interrupted while waiting.");
                Thread.currentThread().interrupt();
            } finally {
                executor.shutdown();
                try {
                    // Give the executor a small grace period to fully terminate
                    if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                        executor.shutdownNow();
                    }
                } catch (InterruptedException e) {
                    executor.shutdownNow();
                }
            }
        }
        System.out.println("Main thread continues execution.");
    }
}