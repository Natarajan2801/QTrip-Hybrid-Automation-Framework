package com.qtrip.pages;

import com.qtrip.base.BasePage;
import com.qtrip.enums.WaitStrategy;
import org.openqa.selenium.By;
import java.util.UUID;

/**
 * Page Object for User Registration functionality.
 * Handles user registration with dynamic email generation support.
 */
public class RegisterPage extends BasePage {
    private final By txtEmail = By.name("email");
    private final By txtPassword = By.name("password");
    private final By txtConfirm = By.name("confirmpassword");
    private final By btnRegister = By.xpath("//button[text()='Register Now']");

    /**
     * Registers a new user with the provided credentials.
     *
     * @param email Base email or username
     * @param password User password
     * @param makeDynamic If true, generates unique email using UUID
     * @return The actual email used for registration
     */
    public String registerNewUser(String email, String password, boolean makeDynamic) {
        String actualEmail = makeDynamic ? generateUniqueEmail() : email;
        sendKeys(txtEmail, actualEmail, WaitStrategy.VISIBLE, "Email");
        sendKeys(txtPassword, password, WaitStrategy.VISIBLE, "Password");
        sendKeys(txtConfirm, password, WaitStrategy.VISIBLE, "Confirm Password");
        click(btnRegister, WaitStrategy.CLICKABLE, "Register Button");
        return actualEmail;
    }

    /**
     * Generates a unique email address using UUID.
     *
     * @return Unique email address
     */
    private String generateUniqueEmail() {
        return "test" + UUID.randomUUID().toString().substring(0, 8) + "@test.com";
    }
}