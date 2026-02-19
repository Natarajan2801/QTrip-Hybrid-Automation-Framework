package com.qtrip.utils;

import com.qtrip.driver.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * JavaScript utility methods for advanced browser interactions.
 * Useful when standard Selenium methods don't work.
 *
 * @author Natarajan M
 */
public final class JavaScriptUtils {

    private JavaScriptUtils() {}

    /**
     * Execute JavaScript and return result.
     */
    public static Object executeScript(String script, Object... args) {
        return getExecutor().executeScript(script, args);
    }

    /**
     * Click element using JavaScript.
     */
    public static void clickElement(WebElement element) {
        getExecutor().executeScript("arguments[0].click();", element);
    }

    /**
     * Scroll to element.
     */
    public static void scrollToElement(WebElement element) {
        getExecutor().executeScript(
            "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }

    /**
     * Scroll to top of page.
     */
    public static void scrollToTop() {
        getExecutor().executeScript("window.scrollTo(0, 0);");
    }

    /**
     * Scroll to bottom of page.
     */
    public static void scrollToBottom() {
        getExecutor().executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    /**
     * Scroll by specific pixels.
     */
    public static void scrollByPixels(int x, int y) {
        getExecutor().executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
    }

    /**
     * Set value to input field using JavaScript.
     */
    public static void setValue(WebElement element, String value) {
        getExecutor().executeScript("arguments[0].value = arguments[1];", element, value);
    }

    /**
     * Clear input field using JavaScript.
     */
    public static void clearField(WebElement element) {
        getExecutor().executeScript("arguments[0].value = '';", element);
    }

    /**
     * Get text content using JavaScript.
     */
    public static String getTextContent(WebElement element) {
        return (String) getExecutor().executeScript("return arguments[0].textContent;", element);
    }

    /**
     * Highlight element for debugging.
     */
    public static void highlightElement(WebElement element) {
        String originalStyle = element.getAttribute("style");
        getExecutor().executeScript(
            "arguments[0].setAttribute('style', arguments[1]);",
            element, "border: 3px solid red; background: yellow;");

        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {}

        getExecutor().executeScript(
            "arguments[0].setAttribute('style', arguments[1]);", element, originalStyle);
    }

    /**
     * Wait for page to fully load.
     */
    public static void waitForPageLoad(int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutSeconds));
        wait.until((ExpectedCondition<Boolean>) driver ->
            getExecutor().executeScript("return document.readyState").equals("complete"));
    }

    /**
     * Wait for jQuery to complete (if present).
     */
    public static void waitForJQuery(int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutSeconds));
        wait.until((ExpectedCondition<Boolean>) driver -> {
            try {
                return (Boolean) getExecutor().executeScript("return jQuery.active == 0");
            } catch (Exception e) {
                return true; // jQuery not present
            }
        });
    }

    /**
     * Check if element is in viewport.
     */
    public static boolean isElementInViewport(WebElement element) {
        return (Boolean) getExecutor().executeScript(
            "var rect = arguments[0].getBoundingClientRect();" +
            "return (rect.top >= 0 && rect.left >= 0 && " +
            "rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) && " +
            "rect.right <= (window.innerWidth || document.documentElement.clientWidth));", element);
    }

    /**
     * Remove element from DOM.
     */
    public static void removeElement(WebElement element) {
        getExecutor().executeScript("arguments[0].remove();", element);
    }

    /**
     * Get page title using JavaScript.
     */
    public static String getPageTitle() {
        return (String) getExecutor().executeScript("return document.title;");
    }

    /**
     * Open new tab.
     */
    public static void openNewTab(String url) {
        getExecutor().executeScript("window.open(arguments[0], '_blank');", url);
    }

    /**
     * Get current URL using JavaScript.
     */
    public static String getCurrentUrl() {
        return (String) getExecutor().executeScript("return window.location.href;");
    }

    /**
     * Refresh page using JavaScript.
     */
    public static void refreshPage() {
        getExecutor().executeScript("location.reload();");
    }

    private static JavascriptExecutor getExecutor() {
        return (JavascriptExecutor) DriverManager.getDriver();
    }
}

