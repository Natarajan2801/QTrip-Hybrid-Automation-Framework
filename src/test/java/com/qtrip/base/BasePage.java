package com.qtrip.base;

import com.qtrip.driver.DriverManager;
import com.qtrip.enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected void click(By by, WaitStrategy strategy, String elementName) {
        try {
            performExplicitWait(strategy, by).click();
        } catch (StaleElementReferenceException e) {
            performExplicitWait(strategy, by).click(); // Self-healing retry
        }
    }
    protected void sendKeys(By by, String value, WaitStrategy strategy, String elementName) {
        try {
            WebElement element = performExplicitWait(strategy, by);
            element.clear();
            element.sendKeys(value);
        } catch (StaleElementReferenceException e) {
            WebElement element = performExplicitWait(strategy, by);
            element.clear();
            element.sendKeys(value);
        }
    }
    protected boolean isDisplayed(By by) {
        try { return performExplicitWait(WaitStrategy.VISIBLE, by).isDisplayed(); }
        catch (Exception e) { return false; }
    }
    protected String getText(By by, WaitStrategy strategy) {
        return performExplicitWait(strategy, by).getText();
    }

    protected int getSize(By by, WaitStrategy strategy) {
        return performExplicitWait(strategy, by).findElements(by).size();
    }

    private WebElement performExplicitWait(WaitStrategy strategy, By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        switch (strategy) {
            case CLICKABLE: return wait.until(ExpectedConditions.elementToBeClickable(by));
            case VISIBLE: return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            case PRESENCE: return wait.until(ExpectedConditions.presenceOfElementLocated(by));
            case NONE: default: return DriverManager.getDriver().findElement(by);
        }
    }
}