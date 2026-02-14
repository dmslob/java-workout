package com.dmslob.tasks;

import java.util.Random;
import java.util.function.Supplier;

public class ApiRetryHandlerTest {
    private static final int MAX_RETRIES = 5;
    private static final long INITIAL_DELAY_MS = 500;
    private static final double MULTIPLIER = 2.0;
    private static final long MAX_DELAY_MS = 10000;
    private static final Random RANDOM = new Random();

    /**
     * Retries a potentially failing action with exponential backoff and jitter.
     *
     * @param <T>    The type of the result returned by the action.
     * @param action The operation to attempt.
     * @return The result of the successful operation.
     * @throws RuntimeException if max retries are reached.
     */
    public <T> T retryWithBackoff(Supplier<T> action) {
        long currentDelay = INITIAL_DELAY_MS;

        for (int attempt = 0; attempt < MAX_RETRIES; attempt++) {
            try {
                // Attempt the actual API call
                System.out.println("Attempt #" + (attempt + 1) + "...");
                return action.get();
            } catch (Exception e) {
                // Handle expected transient errors (e.g., HTTP 429, 500, 503)
                System.err.println("Attempt failed due to: " + e.getMessage());
                // Don't retry for non-transient errors (e.g., HTTP 400 Bad Request, 401 Unauthorized)
                // For a real API, you'd check specific error codes here.
                if (attempt == MAX_RETRIES - 1) {
                    throw new RuntimeException("Max retries reached, operation failed permanently", e);
                }
                // Calculate the next delay with jitter and cap
                long backoff = (long) (INITIAL_DELAY_MS * Math.pow(MULTIPLIER, attempt));
                long jitter = (long) (RANDOM.nextDouble() * INITIAL_DELAY_MS); // Add a random factor
                long delay = Math.min(MAX_DELAY_MS, backoff + jitter);

                System.out.println("Waiting for %s ms before next attempt.".formatted(delay));

                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt(); // Restore the interrupt flag
                    throw new RuntimeException("Retry wait interrupted", ie);
                }
            }
        }
        // This part should be unreachable due to the final exception in the loop
        return null;
    }
}