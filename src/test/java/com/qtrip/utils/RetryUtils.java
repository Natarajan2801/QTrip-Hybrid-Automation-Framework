package com.qtrip.utils;

import java.util.function.Supplier;

/**
 * Retry utility with exponential backoff for handling transient failures.
 * Useful for both UI and API operations that may fail intermittently.
 *
 * @author Natarajan M
 */
public final class RetryUtils {

    private static final int DEFAULT_MAX_RETRIES = 3;
    private static final long DEFAULT_INITIAL_DELAY_MS = 1000;
    private static final double DEFAULT_MULTIPLIER = 2.0;

    private RetryUtils() {}

    /**
     * Execute action with default retry settings.
     */
    public static <T> T executeWithRetry(Supplier<T> action) {
        return executeWithRetry(action, DEFAULT_MAX_RETRIES);
    }

    /**
     * Execute action with specified retry count.
     */
    public static <T> T executeWithRetry(Supplier<T> action, int maxRetries) {
        return executeWithRetry(action, maxRetries, DEFAULT_INITIAL_DELAY_MS);
    }

    /**
     * Execute action with retry and exponential backoff.
     */
    public static <T> T executeWithRetry(Supplier<T> action, int maxRetries, long initialDelayMs) {
        return executeWithRetry(action, maxRetries, initialDelayMs, DEFAULT_MULTIPLIER);
    }

    /**
     * Execute action with full retry configuration.
     */
    public static <T> T executeWithRetry(Supplier<T> action, int maxRetries,
                                          long initialDelayMs, double multiplier) {
        Exception lastException = null;
        long delay = initialDelayMs;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                return action.get();
            } catch (Exception e) {
                lastException = e;
                System.out.println(String.format(
                    "⚠️ Attempt %d/%d failed: %s. Retrying in %dms...",
                    attempt, maxRetries, e.getMessage(), delay));

                if (attempt < maxRetries) {
                    sleep(delay);
                    delay = (long) (delay * multiplier);
                }
            }
        }

        throw new RuntimeException(
            String.format("Action failed after %d attempts", maxRetries), lastException);
    }

    /**
     * Execute void action with retry.
     */
    public static void executeWithRetry(Runnable action) {
        executeWithRetry(action, DEFAULT_MAX_RETRIES);
    }

    /**
     * Execute void action with specified retry count.
     */
    public static void executeWithRetry(Runnable action, int maxRetries) {
        executeWithRetry(() -> {
            action.run();
            return null;
        }, maxRetries);
    }

    /**
     * Execute action until condition is met or timeout.
     */
    public static <T> T executeUntil(Supplier<T> action, java.util.function.Predicate<T> condition,
                                      int maxAttempts, long delayMs) {
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            T result = action.get();
            if (condition.test(result)) {
                return result;
            }
            if (attempt < maxAttempts) {
                System.out.println(String.format(
                    "⏳ Condition not met. Attempt %d/%d. Waiting %dms...",
                    attempt, maxAttempts, delayMs));
                sleep(delayMs);
            }
        }
        throw new RuntimeException("Condition was not met after " + maxAttempts + " attempts");
    }

    /**
     * Execute action ignoring specific exception types.
     */
    @SafeVarargs
    public static <T> T executeIgnoring(Supplier<T> action, Class<? extends Exception>... ignoredExceptions) {
        try {
            return action.get();
        } catch (Exception e) {
            for (Class<? extends Exception> ignored : ignoredExceptions) {
                if (ignored.isInstance(e)) {
                    return null;
                }
            }
            throw e;
        }
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

