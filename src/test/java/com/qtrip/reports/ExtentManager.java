package com.qtrip.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;

public final class ExtentManager {
    private ExtentManager() {}
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static void initReports() {
        if (extent == null) {
            // --- FIX: Ensure directory exists ---
            File reportDir = new File("reports");
            if (!reportDir.exists()) {
                reportDir.mkdirs();
            }
            // ------------------------------------

            extent = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter("reports/QTripReport.html");
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("QTrip Automation Report");
            spark.config().setReportName("QTrip Functional Testing");
            extent.attachReporter(spark);
        }
    }

    public static void createTest(String testName) {
        test.set(extent.createTest(testName));
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}