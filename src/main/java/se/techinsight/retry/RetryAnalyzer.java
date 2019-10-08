package se.techinsight.retry;

import lombok.extern.slf4j.Slf4j;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

@Slf4j
public class RetryAnalyzer implements IRetryAnalyzer {

//    https://github.com/cbeust/testng/issues/1941

    private static final int MAX_RETRY_COUNT = Integer.parseInt(System.getProperty("selenium.max-retry-count", "3"));
    private int count = MAX_RETRY_COUNT;

    @Override
    public boolean retry(ITestResult result) {
        boolean retryAgain = false;
        if (count > 1) {
            log.info("Retrying test '{}', {} out of {}", result.getMethod().getMethodName(),
                MAX_RETRY_COUNT - count + 1,
                MAX_RETRY_COUNT
            );
            retryAgain = true;
            --count;
        }
        return retryAgain;
    }
}