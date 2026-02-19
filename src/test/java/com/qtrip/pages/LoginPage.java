package com.qtrip.pages;

import com.qtrip.base.BasePage;
import com.qtrip.enums.WaitStrategy;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private final By txtEmail = By.name("email");
    private final By txtPassword = By.name("password");
    private final By btnLogin = By.xpath("//button[text()='Login to QTrip']");

    /**
     * Performs user login with provided credentials.
     * Uses smart wait strategy to ensure elements are ready.
     *
     * @param email User email address
     * @param password User password
     */
    public void performLogin(String email, String password) {
        sendKeys(txtEmail, email, WaitStrategy.VISIBLE, "Email");
        sendKeys(txtPassword, password, WaitStrategy.VISIBLE, "Password");
        click(btnLogin, WaitStrategy.CLICKABLE, "Login Button");
    }
}