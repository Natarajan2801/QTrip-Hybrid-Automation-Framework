package com.qtrip.driver;

import com.qtrip.config.EnvironmentManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public final class DriverFactory {
    private DriverFactory() {}

    public static void initDriver() {
        if (DriverManager.getDriver() == null) {
            String browser = EnvironmentManager.get("browser");
            WebDriver driver = null;

            if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--force-device-scale-factor=0.75");
                options.addArguments("start-maximized");

                if (Boolean.parseBoolean(EnvironmentManager.get("headless", "false"))) {
                    options.addArguments("--headless");
                }
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);

            } else if (browser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
            }

            int implicitWait = Integer.parseInt(EnvironmentManager.get("timeout.implicit", "5"));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
            DriverManager.setDriver(driver);
        }
    }

    public static void quitDriver() {
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }
}