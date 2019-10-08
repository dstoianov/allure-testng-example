package se.techinsight.listeners;

import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.util.Arrays;


/**
 * Created by Funker on 15.07.2015.
 */
@Slf4j
public class CustomListener extends TestListenerAdapter {

//    private static final Logger log = LoggerFactory.getLogger(CustomListener.class);

    @Override
    public void onStart(ITestContext context) {
        log.info("Test suite: {}", context.getName());
        log.info("Parameters {}", context.getCurrentXmlTest().getAllParameters().toString());
    }

    @Override
    public void onTestStart(ITestResult result) {
        String[] methodsDependedUpon = result.getMethod().getMethodsDependedUpon();

        //print test class name
        log.info(result.getMethod().getTestClass().toString());

        log.info("<< {} >>", result.getName());
        if (result.getParameters().length > 0) {
            log.info("Test parameters {}", Arrays.toString(result.getParameters()));
        }
        if (methodsDependedUpon != null && methodsDependedUpon.length > 0) {
            log.info("Depends on {}", Arrays.toString(methodsDependedUpon));
        }
        super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("{} --- SUCCESS ---\n", result.getName());
        super.onTestSuccess(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error(" {} --- FAILED --- \n", result.getName());
        Throwable ex = result.getThrowable();
        if (ex != null) {
            String cause = ex.toString();
            log.error(cause + "\n");
        }
        super.onTestFailure(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("{} --- SKIPPED ---\n", result.getName());
        Throwable ex = result.getThrowable();
        if (ex != null) {
            String cause = ex.toString();
            log.error(cause + "\n");
        }
        super.onTestSkipped(result);
    }

}
