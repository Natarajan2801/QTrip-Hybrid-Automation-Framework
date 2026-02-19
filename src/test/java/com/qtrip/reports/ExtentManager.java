package com.qtrip.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Extent Reports Manager for generating rich HTML test reports.
 * Thread-safe implementation for parallel test execution.
 *
 * @author Natarajan M
 */
public final class ExtentManager {

    private ExtentManager() {}

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    /**
     * Initialize Extent Reports with configuration.
     */
    public static void initReports() {
        if (extent == null) {
            File reportDir = new File("reports");
            if (!reportDir.exists()) {
                reportDir.mkdirs();
            }

            extent = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter("reports/QTripReport.html");

            // Configure report appearance
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("QTrip Automation Report");
            spark.config().setReportName("QTrip Test Execution Report");
            spark.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");

            extent.attachReporter(spark);

            // Add system/environment information
            extent.setSystemInfo("Application", "QTrip Dynamic");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Framework", "Selenium + TestNG");
            extent.setSystemInfo("Browser", System.getProperty("browser", "Chrome"));
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("Author", "Natarajan M");
            extent.setSystemInfo("Execution Time", LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
    }

    /**
     * Create a new test entry in the report.
     */
    public static void createTest(String testName) {
        test.set(extent.createTest(testName));
    }

    /**
     * Get the current test instance (thread-safe).
     */
    public static ExtentTest getTest() {
        return test.get();
    }

    /**
     * Flush reports to disk.
     */
    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}