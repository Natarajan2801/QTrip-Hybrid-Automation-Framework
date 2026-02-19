package com.qtrip.utils;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;

/**
 * Custom assertions utility for both UI and API testing.
 * Provides fluent assertion methods with better error messages.
 *
 * @author Natarajan M
 */
public final class AssertionUtils {

    private AssertionUtils() {}

    // ==================== HARD ASSERTIONS ====================

    /**
     * Assert equals with custom message.
     */
    public static void assertEquals(Object actual, Object expected, String description) {
        Assert.assertEquals(actual, expected,
            String.format("❌ Assertion Failed: %s | Expected: '%s' | Actual: '%s'",
                description, expected, actual));
    }

    /**
     * Assert true with custom message.
     */
    public static void assertTrue(boolean condition, String description) {
        Assert.assertTrue(condition,
            String.format("❌ Assertion Failed: %s | Expected: true | Actual: false", description));
    }

    /**
     * Assert false with custom message.
     */
    public static void assertFalse(boolean condition, String description) {
        Assert.assertFalse(condition,
            String.format("❌ Assertion Failed: %s | Expected: false | Actual: true", description));
    }

    /**
     * Assert not null with custom message.
     */
    public static void assertNotNull(Object object, String description) {
        Assert.assertNotNull(object,
            String.format("❌ Assertion Failed: %s | Object should not be null", description));
    }

    /**
     * Assert null with custom message.
     */
    public static void assertNull(Object object, String description) {
        Assert.assertNull(object,
            String.format("❌ Assertion Failed: %s | Object should be null but was: %s", description, object));
    }

    /**
     * Assert contains with custom message.
     */
    public static void assertContains(String actual, String expectedSubstring, String description) {
        Assert.assertTrue(actual != null && actual.contains(expectedSubstring),
            String.format("❌ Assertion Failed: %s | '%s' should contain '%s'",
                description, actual, expectedSubstring));
    }

    /**
     * Assert not empty with custom message.
     */
    public static void assertNotEmpty(String actual, String description) {
        Assert.assertTrue(actual != null && !actual.trim().isEmpty(),
            String.format("❌ Assertion Failed: %s | String should not be empty", description));
    }

    /**
     * Assert list not empty.
     */
    public static void assertListNotEmpty(List<?> list, String description) {
        Assert.assertTrue(list != null && !list.isEmpty(),
            String.format("❌ Assertion Failed: %s | List should not be empty", description));
    }

    /**
     * Assert list size equals.
     */
    public static void assertListSize(List<?> list, int expectedSize, String description) {
        int actualSize = list != null ? list.size() : 0;
        Assert.assertEquals(actualSize, expectedSize,
            String.format("❌ Assertion Failed: %s | Expected size: %d | Actual size: %d",
                description, expectedSize, actualSize));
    }

    // ==================== API ASSERTIONS ====================

    /**
     * Assert API status code.
     */
    public static void assertStatusCode(Response response, int expectedCode, String description) {
        Assert.assertEquals(response.getStatusCode(), expectedCode,
            String.format("❌ API Assertion Failed: %s | Expected: %d | Actual: %d | Body: %s",
                description, expectedCode, response.getStatusCode(), response.getBody().asString()));
    }

    /**
     * Assert API response contains field.
     */
    public static void assertResponseContainsField(Response response, String jsonPath, String description) {
        Object value = response.jsonPath().get(jsonPath);
        Assert.assertNotNull(value,
            String.format("❌ API Assertion Failed: %s | Field '%s' not found in response",
                description, jsonPath));
    }

    /**
     * Assert API response field value.
     */
    public static void assertResponseFieldEquals(Response response, String jsonPath, Object expected, String description) {
        Object actual = response.jsonPath().get(jsonPath);
        Assert.assertEquals(actual, expected,
            String.format("❌ API Assertion Failed: %s | Field: %s | Expected: %s | Actual: %s",
                description, jsonPath, expected, actual));
    }

    /**
     * Assert response time is within limit.
     */
    public static void assertResponseTimeWithin(Response response, long maxMilliseconds, String description) {
        long actualTime = response.getTime();
        Assert.assertTrue(actualTime <= maxMilliseconds,
            String.format("❌ API Assertion Failed: %s | Response time %dms exceeds limit of %dms",
                description, actualTime, maxMilliseconds));
    }

    /**
     * Assert API success response.
     */
    public static void assertApiSuccess(Response response, String description) {
        assertStatusCode(response, 200, description);
        Boolean success = response.jsonPath().getBoolean("success");
        Assert.assertTrue(success != null && success,
            String.format("❌ API Assertion Failed: %s | Expected success=true", description));
    }

    // ==================== SOFT ASSERTIONS ====================

    /**
     * Create new SoftAssert instance.
     */
    public static SoftAssert createSoftAssert() {
        return new SoftAssert();
    }

    /**
     * Soft assert equals.
     */
    public static void softAssertEquals(SoftAssert softAssert, Object actual, Object expected, String description) {
        softAssert.assertEquals(actual, expected,
            String.format("❌ %s | Expected: '%s' | Actual: '%s'", description, expected, actual));
    }

    /**
     * Soft assert true.
     */
    public static void softAssertTrue(SoftAssert softAssert, boolean condition, String description) {
        softAssert.assertTrue(condition,
            String.format("❌ %s | Expected: true | Actual: false", description));
    }

    // ==================== COMPARISON ASSERTIONS ====================

    /**
     * Assert greater than.
     */
    public static void assertGreaterThan(int actual, int expected, String description) {
        Assert.assertTrue(actual > expected,
            String.format("❌ Assertion Failed: %s | %d should be > %d", description, actual, expected));
    }

    /**
     * Assert less than.
     */
    public static void assertLessThan(int actual, int expected, String description) {
        Assert.assertTrue(actual < expected,
            String.format("❌ Assertion Failed: %s | %d should be < %d", description, actual, expected));
    }

    /**
     * Assert within range.
     */
    public static void assertInRange(int actual, int min, int max, String description) {
        Assert.assertTrue(actual >= min && actual <= max,
            String.format("❌ Assertion Failed: %s | %d should be between %d and %d",
                description, actual, min, max));
    }
}

