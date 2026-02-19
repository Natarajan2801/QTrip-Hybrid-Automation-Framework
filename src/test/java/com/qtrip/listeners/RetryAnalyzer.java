package com.qtrip.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Retry Analyzer for automatically retrying failed tests.
 * Helps reduce flaky test failures by retrying tests that fail due to timing issues.
 *
 * @author Natarajan M
 */
public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int MAX_RETRY_COUNT = 2;

    /**
     * Determines whether a test should be retried after failure.
     *
     * @param result The result of the test method that just ran
     * @return true if the test should be retried, false otherwise
     */
    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            System.out.println("[RETRY] Test '" + result.getName() +
                "' failed. Retrying attempt " + retryCount + " of " + MAX_RETRY_COUNT);
            return true;
        }
        return false;
    }
}