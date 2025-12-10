package com.qtrip.pages;

import com.qtrip.base.BasePage;
import com.qtrip.enums.WaitStrategy;
import org.openqa.selenium.By;

public class AdventureDetailsPage extends BasePage {
    private final By txtName = By.name("name");
    private final By txtDate = By.name("date");
    private final By txtCount = By.name("person");
    private final By btnReserve = By.className("reserve-button");
    private final By msgSuccess = By.id("reserved-banner");
    private final By linkHistory = By.linkText("Reservations");

    public void bookAdventure(String name, String date, String count) {
        sendKeys(txtName, name, WaitStrategy.VISIBLE, "Name");
        sendKeys(txtDate, date, WaitStrategy.NONE, "Date");
        sendKeys(txtCount, count, WaitStrategy.NONE, "Count");
        click(btnReserve, WaitStrategy.CLICKABLE, "Reserve Button");
    }
    public boolean isBookingSuccessful() { return isDisplayed(msgSuccess); }
    public HistoryPage navigateToHistory() {
        click(linkHistory, WaitStrategy.CLICKABLE, "History Link");
        return new HistoryPage();
    }
}