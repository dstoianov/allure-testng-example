package browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import se.techinsight.steps.WebDriverSteps;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Funker on 30.05.2016.
 */
@Slf4j
public class BrowserBaseTest {

    protected static final Map<Long, WebDriver> DRIVER_MAP = new ConcurrentHashMap<>();

    protected WebDriverSteps steps;

    protected static WebDriver getDriver() {
        return DRIVER_MAP.get(Thread.currentThread().getId());
    }

    @BeforeClass
    public void setUpPath() {
        WebDriverManager.chromedriver().setup();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (steps != null) {
            steps.quit();
            DRIVER_MAP.remove(Thread.currentThread().getId());
        }
    }
}
