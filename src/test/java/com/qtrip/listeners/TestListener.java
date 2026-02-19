package com.qtrip.listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.qtrip.reports.ExtentManager;
import com.qtrip.utils.ScreenshotUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestNG Listener for comprehensive test lifecycle management.
 * Handles reporting, screenshots, and test execution logging.
 *
 * @author Natarajan M
 */
public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        ExtentManager.initReports();
        System.out.println("=".repeat(60));
        System.out.println("🚀 Starting Test Suite: " + context.getName());
        System.out.println("=".repeat(60));
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentManager.createTest(result.getMethod().getMethodName());
        System.out.println("\n▶️ Starting Test: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.getTest().pass("✅ Test Passed");
        System.out.println("✅ PASSED: " + result.getMethod().getMethodName() +
            " [" + getExecutionTime(result) + "ms]");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            ExtentManager.getTest().fail(result.getThrowable(),
                MediaEntityBuilder.createScreenCaptureFromBase64String(
                    ScreenshotUtils.getBase64Image()).build());
        } catch (Exception e) {
            ExtentManager.getTest().fail(result.getThrowable());
        }
        System.out.println("❌ FAILED: " + result.getMethod().getMethodName() +
            " - " + result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentManager.getTest().skip("⏭️ Test Skipped: " + result.getThrowable().getMessage());
        System.out.println("⏭️ SKIPPED: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.flushReports();
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📊 Test Suite Completed: " + context.getName());
        System.out.println("   ✅ Passed: " + context.getPassedTests().size());
        System.out.println("   ❌ Failed: " + context.getFailedTests().size());
        System.out.println("   ⏭️ Skipped: " + context.getSkippedTests().size());
        System.out.println("=".repeat(60));
    }

    private long getExecutionTime(ITestResult result) {
        return result.getEndMillis() - result.getStartMillis();
    }
}