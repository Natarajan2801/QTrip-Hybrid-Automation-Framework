package com.qtrip.base;

import com.qtrip.config.ConfigLoader;
import com.qtrip.driver.DriverFactory;
import com.qtrip.driver.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverFactory.initDriver();
        DriverManager.getDriver().get(ConfigLoader.get("url"));
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}