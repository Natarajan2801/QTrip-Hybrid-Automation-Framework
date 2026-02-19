package com.qtrip.utils;

import com.qtrip.driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Enhanced Screenshot utilities for test evidence and debugging.
 * Supports multiple formats and storage options.
 *
 * @author Natarajan M
 */
public final class ScreenshotUtils {

    private static final String SCREENSHOT_DIR = "screenshots/";

    private ScreenshotUtils() {}

    /**
     * Get screenshot as Base64 string (for embedding in reports).
     */
    public static String getBase64Image() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
    }

    /**
     * Save screenshot to file with timestamp.
     */
    public static String saveScreenshot(String testName) {
        try {
            createScreenshotDirectory();
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = SCREENSHOT_DIR + testName + "_" + timestamp + ".png";

            File srcFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            File destFile = new File(fileName);
            FileUtils.copyFile(srcFile, destFile);

            return destFile.getAbsolutePath();
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
            return null;
        }
    }

    /**
     * Get screenshot as byte array.
     */
    public static byte[] getScreenshotAsBytes() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    /**
     * Create screenshot directory if it doesn't exist.
     */
    private static void createScreenshotDirectory() {
        File dir = new File(SCREENSHOT_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * Delete all screenshots older than specified days.
     */
    public static void cleanupOldScreenshots(int daysOld) {
        File dir = new File(SCREENSHOT_DIR);
        if (dir.exists()) {
            long cutoffTime = System.currentTimeMillis() - ((long) daysOld * 24 * 60 * 60 * 1000);
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.lastModified() < cutoffTime) {
                        file.delete();
                    }
                }
            }
        }
    }
}