package com.qtrip.base;

import com.qtrip.config.EnvironmentManager;
import com.qtrip.driver.DriverManager;
import com.qtrip.enums.WaitStrategy;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

/**
 * Base Page class providing common web element interactions.
 * Features self-healing mechanisms, smart waits, and JavaScript fallbacks.
 *
 * @author Natarajan M
 * @version 2.0
 */
public class BasePage {

    private static final int MAX_RETRY_ATTEMPTS = 2;

    /**
     * Click on an element with self-healing retry mechanism.
     */
    protected void click(By by, WaitStrategy strategy, String elementName) {
        retryOnStaleElement(() -> {
            WebElement element = performExplicitWait(strategy, by);
            scrollToElement(element);
            element.click();
        }, elementName);
    }

    /**
     * Type text into an element with automatic clearing.
     */
    protected void sendKeys(By by, String value, WaitStrategy strategy, String elementName) {
        retryOnStaleElement(() -> {
            WebElement element = performExplicitWait(strategy, by);
            scrollToElement(element);
            element.clear();
            element.sendKeys(value);
        }, elementName);
    }

    /**
     * Check if element is displayed on the page.
     */
    protected boolean isDisplayed(By by) {
        try {
            return performExplicitWait(WaitStrategy.VISIBLE, by).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get text content from an element.
     */
    protected String getText(By by, WaitStrategy strategy) {
        return performExplicitWait(strategy, by).getText().trim();
    }

    /**
     * Get count of elements matching the locator.
     */
    protected int getSize(By by, WaitStrategy strategy) {
        try {
            performExplicitWait(strategy, by);
            List<WebElement> elements = DriverManager.getDriver().findElements(by);
            return elements.size();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Get attribute value from an element.
     */
    protected String getAttribute(By by, String attribute, WaitStrategy strategy) {
        return performExplicitWait(strategy, by).getAttribute(attribute);
    }

    /**
     * Wait for element to disappear from the page.
     */
    protected boolean waitForElementToDisappear(By by, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutSeconds));
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Perform explicit wait based on the specified strategy.
     */
    private WebElement performExplicitWait(WaitStrategy strategy, By by) {
        int waitTime = Integer.parseInt(EnvironmentManager.get("timeout.explicit", "10"));
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(waitTime));

        switch (strategy) {
            case CLICKABLE:
                return wait.until(ExpectedConditions.elementToBeClickable(by));
            case VISIBLE:
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            case PRESENCE:
                return wait.until(ExpectedConditions.presenceOfElementLocated(by));
            case NONE:
            default:
                return DriverManager.getDriver().findElement(by);
        }
    }

    /**
     * Self-healing retry mechanism for stale element handling.
     */
    private void retryOnStaleElement(Runnable action, String elementName) {
        int attempt = 0;
        while (attempt < MAX_RETRY_ATTEMPTS) {
            try {
                action.run();
                return;
            } catch (StaleElementReferenceException e) {
                attempt++;
                if (attempt >= MAX_RETRY_ATTEMPTS) {
                    throw new RuntimeException("Element '" + elementName + "' is stale after " + MAX_RETRY_ATTEMPTS + " retry attempts", e);
                }
            }
        }
    }

    /**
     * Scroll element into view using JavaScript.
     */
    private void scrollToElement(WebElement element) {
        try {
            ((JavascriptExecutor) DriverManager.getDriver())
                .executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        } catch (Exception ignored) {
            // Scroll is best-effort, don't fail if it doesn't work
        }
    }

    /**
     * Click element using JavaScript (fallback for stubborn elements).
     */
    protected void clickWithJS(By by, String elementName) {
        WebElement element = performExplicitWait(WaitStrategy.PRESENCE, by);
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].click();", element);
    }
}