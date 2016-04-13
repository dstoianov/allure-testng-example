package custom.listener;

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


//    private static final String TEST_NAME_TEMPLATE = "<< %s >>";

//    private void log(String msg, Object... args) {
//        log.info(String.format(msg, args));
//    }

    @Override
    public void onStart(ITestContext arg0) {
        log.info("Test suite: {}", arg0.getName());
        log.info("Parameters {}", arg0.getCurrentXmlTest().getAllParameters().toString());
    }

    @Override
    public void onTestStart(ITestResult arg0) {
        String[] methodsDependedUpon = arg0.getMethod().getMethodsDependedUpon();

        //print test class name
        log.info(arg0.getMethod().getTestClass().toString());

        log.info("<< %s >>", arg0.getName());
        log.info("Test parameters {}", Arrays.toString(arg0.getParameters()));
        if (methodsDependedUpon != null && methodsDependedUpon.length > 0) {
            log.info("Depends on {}", Arrays.toString(methodsDependedUpon));
        }
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        log.info("{} --- SUCCESS ---\n", tr.getName());
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        log.error(tr.getName() + " --- FAILED --- ");
        Throwable ex = tr.getThrowable();
        if (ex != null) {
            String cause = ex.toString();
            log.error(cause + "\n");
        }
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        log.info("%s --- SKIPPED ---\n", tr.getName());
        Throwable ex = tr.getThrowable();
        if (ex != null) {
            String cause = ex.toString();
            log.error(cause + "\n");
        }
    }

    public long timer(ITestResult tr) {
        return tr.getEndMillis() - tr.getStartMillis();
    }
}
