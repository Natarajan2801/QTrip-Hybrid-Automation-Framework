package com.qtrip.pages;

import com.qtrip.base.BasePage;
import com.qtrip.enums.WaitStrategy;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private final By txtEmail = By.name("email");
    private final By txtPassword = By.name("password");
    private final By btnLogin = By.xpath("//button[text()='Login to QTrip']");

    public void performLogin(String email, String password) throws InterruptedException {
        Thread.sleep(1000);
        sendKeys(txtEmail, email, WaitStrategy.VISIBLE, "Email");
        sendKeys(txtPassword, password, WaitStrategy.NONE, "Password");
        click(btnLogin, WaitStrategy.CLICKABLE, "Login Button");
    }
}