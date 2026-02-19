package com.qtrip.tests.ui;

import com.qtrip.base.BaseTest;
import com.qtrip.pages.HomePage;
import com.qtrip.pages.LoginPage;
import com.qtrip.pages.RegisterPage;
import com.qtrip.utils.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Map;

/**
 * Test class for User Authentication functionality.
 * Covers: Registration, Login, and Logout flows.
 */
public class AuthenticationTest extends BaseTest {

    @Test(dataProvider = "getData", dataProviderClass = ExcelUtils.class,
          groups = {"Smoke", "Authentication"},
          description = "Verify user registration, login, and logout flow")
    public void verifyRegistrationAndLogin(Map<String, String> data) {
        HomePage home = new HomePage();
        RegisterPage register = home.navigateToRegister();
        String email = register.registerNewUser(data.get("UserName"), data.get("Password"), true);

        LoginPage login = new LoginPage();
        login.performLogin(email, data.get("Password"));

        Assert.assertTrue(home.isUserLoggedIn(), "User should be logged in after valid credentials");
        home.logOutUser();
    }
}