package com.qtrip.utils;

import com.qtrip.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Smart wait utilities for reliable test execution.
 * Provides various wait conditions beyond standard explicit waits.
 *
 * @author Natarajan M
 */
public final class WaitUtils {

    private WaitUtils() {}

    private static final int DEFAULT_TIMEOUT = 10;

    /**
     * Wait for element to be visible.
     */
    public static WebElement waitForVisible(By locator) {
        return waitForVisible(locator, DEFAULT_TIMEOUT);
    }

    public static WebElement waitForVisible(By locator, int timeoutSeconds) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutSeconds))
            .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait for element to be clickable.
     */
    public static WebElement waitForClickable(By locator) {
        return waitForClickable(locator, DEFAULT_TIMEOUT);
    }

    public static WebElement waitForClickable(By locator, int timeoutSeconds) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutSeconds))
            .until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Wait for element to be present in DOM.
     */
    public static WebElement waitForPresence(By locator) {
        return waitForPresence(locator, DEFAULT_TIMEOUT);
    }

    public static WebElement waitForPresence(By locator, int timeoutSeconds) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Wait for element to disappear.
     */
    public static boolean waitForInvisible(By locator) {
        return waitForInvisible(locator, DEFAULT_TIMEOUT);
    }

    public static boolean waitForInvisible(By locator, int timeoutSeconds) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutSeconds))
            .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Wait for text to be present in element.
     */
    public static boolean waitForTextPresent(By locator, String text) {
        return waitForTextPresent(locator, text, DEFAULT_TIMEOUT);
    }

    public static boolean waitForTextPresent(By locator, String text, int timeoutSeconds) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutSeconds))
            .until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    /**
     * Wait for attribute value.
     */
    public static boolean waitForAttribute(By locator, String attribute, String value) {
        return waitForAttribute(locator, attribute, value, DEFAULT_TIMEOUT);
    }

    public static boolean waitForAttribute(By locator, String attribute, String value, int timeoutSeconds) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutSeconds))
            .until(ExpectedConditions.attributeToBe(locator, attribute, value));
    }

    /**
     * Wait for all elements to be visible.
     */
    public static List<WebElement> waitForAllVisible(By locator) {
        return waitForAllVisible(locator, DEFAULT_TIMEOUT);
    }

    public static List<WebElement> waitForAllVisible(By locator, int timeoutSeconds) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutSeconds))
            .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    /**
     * Wait for URL to contain specific text.
     */
    public static boolean waitForUrlContains(String urlPart) {
        return waitForUrlContains(urlPart, DEFAULT_TIMEOUT);
    }

    public static boolean waitForUrlContains(String urlPart, int timeoutSeconds) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutSeconds))
            .until(ExpectedConditions.urlContains(urlPart));
    }

    /**
     * Wait for title to contain specific text.
     */
    public static boolean waitForTitleContains(String title) {
        return waitForTitleContains(title, DEFAULT_TIMEOUT);
    }

    public static boolean waitForTitleContains(String title, int timeoutSeconds) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutSeconds))
            .until(ExpectedConditions.titleContains(title));
    }

    /**
     * Wait for frame and switch to it.
     */
    public static void waitForFrameAndSwitch(By locator) {
        waitForFrameAndSwitch(locator, DEFAULT_TIMEOUT);
    }

    public static void waitForFrameAndSwitch(By locator, int timeoutSeconds) {
        new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutSeconds))
            .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }

    /**
     * Wait for number of windows to be specific count.
     */
    public static boolean waitForWindowCount(int count) {
        return waitForWindowCount(count, DEFAULT_TIMEOUT);
    }

    public static boolean waitForWindowCount(int count, int timeoutSeconds) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutSeconds))
            .until(ExpectedConditions.numberOfWindowsToBe(count));
    }

    /**
     * Wait for element count.
     */
    public static boolean waitForElementCount(By locator, int count) {
        return waitForElementCount(locator, count, DEFAULT_TIMEOUT);
    }

    public static boolean waitForElementCount(By locator, int count, int timeoutSeconds) {
        try {
            new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutSeconds))
                .until(driver -> driver.findElements(locator).size() == count);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Wait with custom polling interval.
     */
    public static WebElement waitWithPolling(By locator, int timeoutSeconds, int pollingMillis) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutSeconds))
            .pollingEvery(Duration.ofMillis(pollingMillis))
            .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait for staleness of element (useful after page refresh).
     */
    public static boolean waitForStaleness(WebElement element) {
        return waitForStaleness(element, DEFAULT_TIMEOUT);
    }

    public static boolean waitForStaleness(WebElement element, int timeoutSeconds) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutSeconds))
            .until(ExpectedConditions.stalenessOf(element));
    }

    /**
     * Simple hard wait (use sparingly).
     */
    public static void hardWait(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


