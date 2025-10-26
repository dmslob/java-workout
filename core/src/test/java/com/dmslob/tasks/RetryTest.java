package com.dmslob.tasks;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RetryTest {
    /**
     * Executes an operation with an exponential backoff retry mechanism.
     * * The initial attempt has no delay. Subsequent delays are doubled.
     * * @param <T> The return type of the operation.
     *
     * @param operation          The operation to execute, represented as a Callable.
     * @param maxAttempts        The maximum number of times to attempt the operation.
     * @param initialDelayMillis The initial delay for the *first* retry (second attempt) in milliseconds.
     * @return The result of the successful operation.
     * @throws Exception The exception from the last failed attempt after maxAttempts is reached.
     */
    <T> T executeWithRetry(Callable<T> operation, int maxAttempts, long initialDelayMillis) throws Exception {
        if (maxAttempts <= 0) {
            throw new IllegalArgumentException("maxAttempts must be greater than 0.");
        }
        long currentDelay = initialDelayMillis;
        Exception lastException = null;
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            if (attempt > 1) {
                log.info("Attempt {} failed. Retrying in {} ms...", attempt - 1, currentDelay);
                try {
                    TimeUnit.MILLISECONDS.sleep(currentDelay);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Retry wait interrupted.", e);
                }
                currentDelay *= 2;
            }
            try {
                return operation.call();
            } catch (Exception e) {
                lastException = e;
                log.info("Attempt {} failed with exception: {}-{}",
                        attempt, e.getClass().getSimpleName(), e.getMessage());
            }
        }
        log.info("Maximum attempts ({}) reached. Throwing last exception.", maxAttempts);
        throw lastException;
    }

    private static int attemptCounter = 0;

    static class FlakyOperation implements Callable<String> {
        private final int failThreshold;
        public FlakyOperation(int failThreshold) {
            this.failThreshold = failThreshold;
        }

        @Override
        public String call() throws Exception {
            attemptCounter++;
            log.info("--- Executing actual operation (Attempt {}) ---", attemptCounter);
            if (attemptCounter < failThreshold) {
                // Simulate a temporary failure
                throw new java.io.IOException("Network connection reset.");
            } else {
                return "Operation succeeded on attempt %s!".formatted(attemptCounter);
            }
        }
    }

    @Test
    public void should_retry_operation() {
        // given
        final int MAX_ATTEMPTS = 5;
        final long INITIAL_DELAY_MS = 500; // 500ms -> 1s -> 2s -> 4s
        attemptCounter = 0;

        log.info("### Test Case 1: Succeeds on 3rd attempt ###");
        try {
            // Operation will fail on attempt 1 and 2, succeed on 3
            String result = executeWithRetry(new FlakyOperation(3), MAX_ATTEMPTS, INITIAL_DELAY_MS);
            log.info("SUCCESS: {}", result);
        } catch (Exception e) {
            log.warn("FAILURE: Unexpected exception in successful test: {}", e.getMessage());
        }
        log.info("-".repeat(50));

        // Reset counter for the second run
        attemptCounter = 0;
        log.info("### Test Case 2: Fails after max attempts ###");
        try {
            // Operation will fail 5 times (max attempts), throwing the last exception
            String result = executeWithRetry(new FlakyOperation(6), MAX_ATTEMPTS, INITIAL_DELAY_MS);
            log.info("SUCCESS: Unexpected success in failure test: {}", result);
        } catch (Exception e) {
            log.warn("FAILURE: Expected exception received: {} - {}", e.getClass().getSimpleName(), e.getMessage());
        }
    }
}