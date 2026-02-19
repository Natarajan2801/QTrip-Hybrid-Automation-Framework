package com.qtrip.context;

import java.util.HashMap;
import java.util.Map;

/**
 * Test Context for sharing data between test steps and classes.
 * Thread-safe implementation for parallel execution.
 *
 * @author Natarajan M
 */
public final class TestContext {

    private static final ThreadLocal<Map<String, Object>> context =
        ThreadLocal.withInitial(HashMap::new);

    private TestContext() {}

    /**
     * Set a value in the context.
     */
    public static void set(String key, Object value) {
        context.get().put(key, value);
    }

    /**
     * Get a value from the context.
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(String key) {
        return (T) context.get().get(key);
    }

    /**
     * Get a value with default if not present.
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(String key, T defaultValue) {
        Object value = context.get().get(key);
        return value != null ? (T) value : defaultValue;
    }

    /**
     * Check if key exists in context.
     */
    public static boolean contains(String key) {
        return context.get().containsKey(key);
    }

    /**
     * Remove a value from context.
     */
    public static void remove(String key) {
        context.get().remove(key);
    }

    /**
     * Clear all values from context.
     */
    public static void clear() {
        context.get().clear();
    }

    /**
     * Get all context data (for debugging).
     */
    public static Map<String, Object> getAll() {
        return new HashMap<>(context.get());
    }

    // ==================== CONVENIENCE METHODS ====================

    /**
     * Store user token for API tests.
     */
    public static void setToken(String token) {
        set("auth_token", token);
    }

    /**
     * Get stored user token.
     */
    public static String getToken() {
        return get("auth_token");
    }

    /**
     * Store user ID.
     */
    public static void setUserId(String userId) {
        set("user_id", userId);
    }

    /**
     * Get stored user ID.
     */
    public static String getUserId() {
        return get("user_id");
    }

    /**
     * Store user email.
     */
    public static void setEmail(String email) {
        set("user_email", email);
    }

    /**
     * Get stored user email.
     */
    public static String getEmail() {
        return get("user_email");
    }

    /**
     * Store booking/transaction ID.
     */
    public static void setTransactionId(String txnId) {
        set("transaction_id", txnId);
    }

    /**
     * Get stored transaction ID.
     */
    public static String getTransactionId() {
        return get("transaction_id");
    }
}

