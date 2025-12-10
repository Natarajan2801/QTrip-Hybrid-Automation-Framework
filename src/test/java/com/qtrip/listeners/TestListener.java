package com.qtrip.listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.qtrip.reports.ExtentManager;
import com.qtrip.utils.ScreenshotUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    @Override
    public void onStart(ITestContext context) { ExtentManager.initReports(); }
    @Override
    public void onTestStart(ITestResult result) { ExtentManager.createTest(result.getMethod().getMethodName()); }
    @Override
    public void onTestSuccess(ITestResult result) { ExtentManager.getTest().pass("Test Passed"); }
    @Override
    public void onTestFailure(ITestResult result) {
        ExtentManager.getTest().fail(result.getThrowable(),
                MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
    }
    @Override
    public void onFinish(ITestContext context) { ExtentManager.flushReports(); }
}