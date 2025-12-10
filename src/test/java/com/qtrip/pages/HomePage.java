package com.qtrip.pages;

import com.qtrip.base.BasePage;
import com.qtrip.enums.WaitStrategy;
import org.openqa.selenium.By;

public class HomePage extends BasePage {
    private final By linkRegister = By.xpath("//a[normalize-space()='Register']");
    private final By linkLogin = By.xpath("//a[normalize-space()='Login']");
    private final By btnLogout = By.xpath("//div[contains(text(),'Logout')]");
    private final By searchBox = By.xpath("//input[contains(@placeholder,'Search a City')]");
    private final By autoComplete = By.id("results");

    public RegisterPage navigateToRegister() {
        click(linkRegister, WaitStrategy.CLICKABLE, "Register Link");
        return new RegisterPage();
    }
    public LoginPage navigateToLogin() {
        click(linkLogin, WaitStrategy.CLICKABLE, "Login Link");
        return new LoginPage();
    }
    public boolean isUserLoggedIn() { return isDisplayed(btnLogout); }
    public void logOutUser() { if (isUserLoggedIn()) click(btnLogout, WaitStrategy.CLICKABLE, "Logout"); }
    public void searchCity(String city) {
        sendKeys(searchBox, city, WaitStrategy.VISIBLE, "Search Box");
        click(autoComplete, WaitStrategy.CLICKABLE, "City Option");
    }

    // Add to existing HomePage class
    public HistoryPage navigateToHistory() {
        // Assuming there is a "Reservations" link in the navbar
        click(By.linkText("Reservations"), WaitStrategy.CLICKABLE, "Reservations Link");
        return new HistoryPage();
    }

    public void navigateToHome() {
        click(By.linkText("Home"), WaitStrategy.CLICKABLE, "Home Link");
    }
}