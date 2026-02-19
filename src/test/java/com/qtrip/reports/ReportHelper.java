package com.qtrip.reports;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.qtrip.utils.ScreenshotUtils;

/**
 * Report Helper for step-by-step test reporting.
 * Provides fluent API for logging test steps with evidence.
 *
 * @author Natarajan M
 */
public final class ReportHelper {

    private ReportHelper() {}

    /**
     * Log info step.
     */
    public static void info(String message) {
        getTest().info(message);
    }

    /**
     * Log pass step.
     */
    public static void pass(String message) {
        getTest().pass(message);
    }

    /**
     * Log fail step.
     */
    public static void fail(String message) {
        getTest().fail(message);
    }

    /**
     * Log fail with exception.
     */
    public static void fail(String message, Throwable t) {
        getTest().fail(message);
        getTest().fail(t);
    }

    /**
     * Log warning step.
     */
    public static void warning(String message) {
        getTest().warning(message);
    }

    /**
     * Log skip step.
     */
    public static void skip(String message) {
        getTest().skip(message);
    }

    /**
     * Log step with screenshot.
     */
    public static void infoWithScreenshot(String message) {
        try {
            getTest().info(message,
                MediaEntityBuilder.createScreenCaptureFromBase64String(
                    ScreenshotUtils.getBase64Image()).build());
        } catch (Exception e) {
            getTest().info(message + " (Screenshot failed)");
        }
    }

    /**
     * Log pass with screenshot.
     */
    public static void passWithScreenshot(String message) {
        try {
            getTest().pass(message,
                MediaEntityBuilder.createScreenCaptureFromBase64String(
                    ScreenshotUtils.getBase64Image()).build());
        } catch (Exception e) {
            getTest().pass(message + " (Screenshot failed)");
        }
    }

    /**
     * Log fail with screenshot.
     */
    public static void failWithScreenshot(String message) {
        try {
            getTest().fail(message,
                MediaEntityBuilder.createScreenCaptureFromBase64String(
                    ScreenshotUtils.getBase64Image()).build());
        } catch (Exception e) {
            getTest().fail(message + " (Screenshot failed)");
        }
    }

    /**
     * Log labeled value (key-value pair).
     */
    public static void logKeyValue(String key, String value) {
        getTest().info(MarkupHelper.createLabel(key + ": " + value, ExtentColor.BLUE));
    }

    /**
     * Log test step with number.
     */
    public static void step(int stepNumber, String description) {
        getTest().info("📌 Step " + stepNumber + ": " + description);
    }

    /**
     * Log API request details.
     */
    public static void logApiRequest(String method, String endpoint, String body) {
        getTest().info(MarkupHelper.createLabel("API Request", ExtentColor.CYAN));
        getTest().info("Method: " + method);
        getTest().info("Endpoint: " + endpoint);
        if (body != null && !body.isEmpty()) {
            getTest().info("Body: " + body);
        }
    }

    /**
     * Log API response details.
     */
    public static void logApiResponse(int statusCode, String body, long responseTime) {
        ExtentColor color = statusCode >= 200 && statusCode < 300 ? ExtentColor.GREEN : ExtentColor.RED;
        getTest().info(MarkupHelper.createLabel("API Response: " + statusCode, color));
        getTest().info("Response Time: " + responseTime + "ms");
        if (body != null) {
            getTest().info("Body: " + (body.length() > 500 ? body.substring(0, 500) + "..." : body));
        }
    }

    /**
     * Create a node/sub-section in report.
     */
    public static ExtentTest createNode(String nodeName) {
        return getTest().createNode(nodeName);
    }

    /**
     * Log custom status.
     */
    public static void log(Status status, String message) {
        getTest().log(status, message);
    }

    /**
     * Add category/tag to test.
     */
    public static void assignCategory(String... categories) {
        getTest().assignCategory(categories);
    }

    /**
     * Add author to test.
     */
    public static void assignAuthor(String... authors) {
        getTest().assignAuthor(authors);
    }

    private static ExtentTest getTest() {
        ExtentTest test = ExtentManager.getTest();
        if (test == null) {
            throw new IllegalStateException("ExtentTest not initialized. Call ExtentManager.createTest() first.");
        }
        return test;
    }
}

