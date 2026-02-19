package com.qtrip.utils;

import com.qtrip.driver.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Advanced browser and element interaction utilities.
 * Provides methods for complex UI interactions like drag-drop, file upload, alerts, etc.
 *
 * @author Natarajan M
 */
public final class BrowserUtils {

    private BrowserUtils() {}

    // ==================== WINDOW MANAGEMENT ====================

    /**
     * Switch to new window/tab.
     */
    public static void switchToNewWindow() {
        String mainWindow = DriverManager.getDriver().getWindowHandle();
        Set<String> allWindows = DriverManager.getDriver().getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(mainWindow)) {
                DriverManager.getDriver().switchTo().window(window);
                break;
            }
        }
    }

    /**
     * Switch to window by title.
     */
    public static void switchToWindowByTitle(String title) {
        Set<String> windows = DriverManager.getDriver().getWindowHandles();
        for (String window : windows) {
            DriverManager.getDriver().switchTo().window(window);
            if (DriverManager.getDriver().getTitle().contains(title)) {
                break;
            }
        }
    }

    /**
     * Close current window and switch to main.
     */
    public static void closeCurrentAndSwitchToMain(String mainWindowHandle) {
        DriverManager.getDriver().close();
        DriverManager.getDriver().switchTo().window(mainWindowHandle);
    }

    /**
     * Get all window handles.
     */
    public static List<String> getAllWindowHandles() {
        return new ArrayList<>(DriverManager.getDriver().getWindowHandles());
    }

    // ==================== FRAMES ====================

    /**
     * Switch to frame by index.
     */
    public static void switchToFrame(int index) {
        DriverManager.getDriver().switchTo().frame(index);
    }

    /**
     * Switch to frame by name or ID.
     */
    public static void switchToFrame(String nameOrId) {
        DriverManager.getDriver().switchTo().frame(nameOrId);
    }

    /**
     * Switch to frame by WebElement.
     */
    public static void switchToFrame(WebElement element) {
        DriverManager.getDriver().switchTo().frame(element);
    }

    /**
     * Switch to parent frame.
     */
    public static void switchToParentFrame() {
        DriverManager.getDriver().switchTo().parentFrame();
    }

    /**
     * Switch to default content.
     */
    public static void switchToDefaultContent() {
        DriverManager.getDriver().switchTo().defaultContent();
    }

    // ==================== ALERTS ====================

    /**
     * Accept alert.
     */
    public static void acceptAlert() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        DriverManager.getDriver().switchTo().alert().accept();
    }

    /**
     * Dismiss alert.
     */
    public static void dismissAlert() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        DriverManager.getDriver().switchTo().alert().dismiss();
    }

    /**
     * Get alert text.
     */
    public static String getAlertText() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        return DriverManager.getDriver().switchTo().alert().getText();
    }

    /**
     * Send text to alert prompt.
     */
    public static void sendKeysToAlert(String text) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        DriverManager.getDriver().switchTo().alert().sendKeys(text);
        DriverManager.getDriver().switchTo().alert().accept();
    }

    /**
     * Check if alert is present.
     */
    public static boolean isAlertPresent() {
        try {
            DriverManager.getDriver().switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    // ==================== DROPDOWNS ====================

    /**
     * Select dropdown by visible text.
     */
    public static void selectByVisibleText(WebElement element, String text) {
        new Select(element).selectByVisibleText(text);
    }

    /**
     * Select dropdown by value.
     */
    public static void selectByValue(WebElement element, String value) {
        new Select(element).selectByValue(value);
    }

    /**
     * Select dropdown by index.
     */
    public static void selectByIndex(WebElement element, int index) {
        new Select(element).selectByIndex(index);
    }

    /**
     * Get all dropdown options.
     */
    public static List<String> getAllDropdownOptions(WebElement element) {
        List<String> options = new ArrayList<>();
        new Select(element).getOptions().forEach(opt -> options.add(opt.getText()));
        return options;
    }

    /**
     * Get selected option text.
     */
    public static String getSelectedOption(WebElement element) {
        return new Select(element).getFirstSelectedOption().getText();
    }

    // ==================== ACTIONS ====================

    /**
     * Hover over element.
     */
    public static void hoverOverElement(WebElement element) {
        new Actions(DriverManager.getDriver()).moveToElement(element).perform();
    }

    /**
     * Double click on element.
     */
    public static void doubleClick(WebElement element) {
        new Actions(DriverManager.getDriver()).doubleClick(element).perform();
    }

    /**
     * Right click on element.
     */
    public static void rightClick(WebElement element) {
        new Actions(DriverManager.getDriver()).contextClick(element).perform();
    }

    /**
     * Drag and drop.
     */
    public static void dragAndDrop(WebElement source, WebElement target) {
        new Actions(DriverManager.getDriver()).dragAndDrop(source, target).perform();
    }

    /**
     * Click and hold.
     */
    public static void clickAndHold(WebElement element) {
        new Actions(DriverManager.getDriver()).clickAndHold(element).perform();
    }

    /**
     * Release click.
     */
    public static void release() {
        new Actions(DriverManager.getDriver()).release().perform();
    }

    /**
     * Send keyboard keys.
     */
    public static void sendKeys(Keys... keys) {
        new Actions(DriverManager.getDriver()).sendKeys(keys).perform();
    }

    /**
     * Press key combination (e.g., Ctrl+A).
     */
    public static void keyboardShortcut(Keys modifier, String key) {
        new Actions(DriverManager.getDriver())
            .keyDown(modifier)
            .sendKeys(key)
            .keyUp(modifier)
            .perform();
    }

    // ==================== COOKIES ====================

    /**
     * Add cookie.
     */
    public static void addCookie(String name, String value) {
        DriverManager.getDriver().manage().addCookie(new Cookie(name, value));
    }

    /**
     * Get cookie by name.
     */
    public static Cookie getCookie(String name) {
        return DriverManager.getDriver().manage().getCookieNamed(name);
    }

    /**
     * Delete cookie by name.
     */
    public static void deleteCookie(String name) {
        DriverManager.getDriver().manage().deleteCookieNamed(name);
    }

    /**
     * Delete all cookies.
     */
    public static void deleteAllCookies() {
        DriverManager.getDriver().manage().deleteAllCookies();
    }

    /**
     * Get all cookies.
     */
    public static Set<Cookie> getAllCookies() {
        return DriverManager.getDriver().manage().getCookies();
    }

    // ==================== NAVIGATION ====================

    /**
     * Navigate to URL.
     */
    public static void navigateTo(String url) {
        DriverManager.getDriver().navigate().to(url);
    }

    /**
     * Navigate back.
     */
    public static void navigateBack() {
        DriverManager.getDriver().navigate().back();
    }

    /**
     * Navigate forward.
     */
    public static void navigateForward() {
        DriverManager.getDriver().navigate().forward();
    }

    /**
     * Refresh page.
     */
    public static void refresh() {
        DriverManager.getDriver().navigate().refresh();
    }

    /**
     * Get current URL.
     */
    public static String getCurrentUrl() {
        return DriverManager.getDriver().getCurrentUrl();
    }

    /**
     * Get page title.
     */
    public static String getPageTitle() {
        return DriverManager.getDriver().getTitle();
    }

    /**
     * Get page source.
     */
    public static String getPageSource() {
        return DriverManager.getDriver().getPageSource();
    }

    // ==================== FILE UPLOAD ====================

    /**
     * Upload file using input element.
     */
    public static void uploadFile(WebElement fileInput, String filePath) {
        fileInput.sendKeys(filePath);
    }
}

