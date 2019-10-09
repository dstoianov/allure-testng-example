package se.techinsight.listeners;

import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.util.Iterator;

/**
 * Sets retired test to pass if test was successful on retrial
 */
public class MyTestListenerAdapter extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result) {
        super.onTestFailure(result);
        if (result.getMethod().getRetryAnalyzer() != null) {
            RetryAnalyzer retryAnalyzer = (RetryAnalyzer) result.getMethod().getRetryAnalyzer();

            if (retryAnalyzer.isRetryAvailable()) {
                result.setStatus(ITestResult.FAILURE);
            } else {
                result.setStatus(ITestResult.FAILURE);
            }
            Reporter.setCurrentTestResult(result);
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        super.onFinish(context);
        Iterator<ITestResult> failedTestCases = context.getFailedTests().getAllResults().iterator();
        while (failedTestCases.hasNext()) {
            ITestResult failedTestCase = failedTestCases.next();
            ITestNGMethod method = failedTestCase.getMethod();
            if (context.getFailedTests().getResults(method).size() > 1) {
                failedTestCases.remove();
            } else {
                if (context.getPassedTests().getResults(method).size() > 0) {
                    failedTestCases.remove();
                }
            }
        }
    }
}