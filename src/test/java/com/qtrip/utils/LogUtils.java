package com.qtrip.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Centralized logging utility for the framework.
 * Provides consistent logging across all test classes and utilities.
 *
 * @author Natarajan M
 */
public final class LogUtils {

    private LogUtils() {}

    /**
     * Get logger for a specific class.
     */
    public static Logger getLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }

    /**
     * Log info message.
     */
    public static void info(Class<?> clazz, String message) {
        getLogger(clazz).info(message);
    }

    /**
     * Log info message with parameters.
     */
    public static void info(Class<?> clazz, String message, Object... params) {
        getLogger(clazz).info(message, params);
    }

    /**
     * Log debug message.
     */
    public static void debug(Class<?> clazz, String message) {
        getLogger(clazz).debug(message);
    }

    /**
     * Log warning message.
     */
    public static void warn(Class<?> clazz, String message) {
        getLogger(clazz).warn(message);
    }

    /**
     * Log error message.
     */
    public static void error(Class<?> clazz, String message) {
        getLogger(clazz).error(message);
    }

    /**
     * Log error message with exception.
     */
    public static void error(Class<?> clazz, String message, Throwable throwable) {
        getLogger(clazz).error(message, throwable);
    }

    /**
     * Log test step for reporting purposes.
     */
    public static void step(Class<?> clazz, String stepDescription) {
        getLogger(clazz).info("📌 STEP: {}", stepDescription);
    }

    /**
     * Log test start.
     */
    public static void testStart(Class<?> clazz, String testName) {
        getLogger(clazz).info("▶️ TEST START: {}", testName);
    }

    /**
     * Log test pass.
     */
    public static void testPass(Class<?> clazz, String testName) {
        getLogger(clazz).info("✅ TEST PASSED: {}", testName);
    }

    /**
     * Log test fail.
     */
    public static void testFail(Class<?> clazz, String testName, String reason) {
        getLogger(clazz).error("❌ TEST FAILED: {} - Reason: {}", testName, reason);
    }
}

