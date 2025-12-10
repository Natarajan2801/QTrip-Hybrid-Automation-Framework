package com.qtrip.pages;

import com.qtrip.base.BasePage;
import com.qtrip.driver.DriverManager;
import com.qtrip.enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class AdventurePage extends BasePage {

    // Locators
    private final By durationFilter = By.id("duration-select");
    private final By categoryFilter = By.id("category-select");
    private final By clearDuration = By.xpath("//div[@onclick='clearDuration(event)']");
    private final By clearCategory = By.xpath("//div[@onclick='clearCategory(event)']");
    private final By searchResultGrid = By.xpath("//div[@class='col-6 col-lg-3 mb-4']");

    // --- Actions ---

    public void selectDurationFilter(String durationText) {
        if (durationText != null && !durationText.isEmpty()) {
            WebElement dropdown = DriverManager.getDriver().findElement(durationFilter);
            Select select = new Select(dropdown);
            select.selectByVisibleText(durationText);
        }
    }

    public void selectCategoryFilter(String categoryText) {
        if (categoryText != null && !categoryText.isEmpty()) {
            WebElement dropdown = DriverManager.getDriver().findElement(categoryFilter);
            Select select = new Select(dropdown);
            select.selectByVisibleText(categoryText);
        }
    }

    public int getResultCount() {
        // Wait for grid to be present (or empty)
        try {
            List<WebElement> results = DriverManager.getDriver().findElements(searchResultGrid);
            return results.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public void clearFilters() {
        click(clearDuration, WaitStrategy.CLICKABLE, "Clear Duration");
        click(clearCategory, WaitStrategy.CLICKABLE, "Clear Category");
    }

    public void selectAdventure(String adventureName) {
        // Dynamic locator for the specific adventure
        click(By.xpath("//h5[text()='" + adventureName + "']"), WaitStrategy.CLICKABLE, adventureName);
    }
}