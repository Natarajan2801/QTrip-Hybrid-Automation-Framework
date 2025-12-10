package com.qtrip.pages;

import com.qtrip.base.BasePage;
import com.qtrip.enums.WaitStrategy;
import org.openqa.selenium.By;
import java.util.UUID;

public class RegisterPage extends BasePage {
    private final By txtEmail = By.name("email");
    private final By txtPassword = By.name("password");
    private final By txtConfirm = By.name("confirmpassword");
    private final By btnRegister = By.xpath("//button[text()='Register Now']");

    public String registerNewUser(String email, String password, boolean makeDynamic) {
        if (makeDynamic) email = "test" + UUID.randomUUID() + "@test.com";
        sendKeys(txtEmail, email, WaitStrategy.VISIBLE, "Email");
        sendKeys(txtPassword, password, WaitStrategy.NONE, "Password");
        sendKeys(txtConfirm, password, WaitStrategy.NONE, "Confirm");
        click(btnRegister, WaitStrategy.CLICKABLE, "Register Button");
        return email;
    }
}