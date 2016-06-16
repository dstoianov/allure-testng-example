package driver;

import enums.OperatingSystem;
import lombok.extern.slf4j.Slf4j;
import my.company.steps.WebDriverSteps;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Funker on 30.05.2016.
 */
@Slf4j
public class BrowserBase {

    protected static final Map<Long, WebDriver> DRIVER_MAP = new ConcurrentHashMap<>();

    protected WebDriverSteps steps;

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (steps != null) {
            steps.quit();
            DRIVER_MAP.remove(Thread.currentThread().getId());
        }
    }

    protected static WebDriver getDriver() {
        return DRIVER_MAP.get(Thread.currentThread().getId());
    }

    @BeforeClass
    public void setUpPath() {
        setSystemPathForDrivers();
    }

    private void setSystemPathForDrivers() {
        String pathHome = System.getProperty("user.dir");
        String path = String.format("%s/src/test/resources/drivers/", pathHome);
        String osName = OperatingSystem.getOperatingSystem().name();

        if (osName.equalsIgnoreCase(OperatingSystem.WINDOWS.name())) {
            System.setProperty("webdriver.chrome.driver", path + "win/chromedriver.exe");
            System.setProperty("webdriver.ie.driver", path + "win/IEDriverServer.exe");
            System.setProperty("phantomjs.binary.path", path + "win/phantomjs.exe");
//          Download the marionette driver here
//          https://developer.mozilla.org/en-US/docs/Mozilla/QA/Marionette/WebDriver
            System.setProperty("webdriver.gecko.driver", path + "win/wires.exe");
        } else if (osName.equalsIgnoreCase(OperatingSystem.MAC.name())) {
            System.setProperty("webdriver.chrome.driver", path + "mac/chromedriver");
        } else {
            System.setProperty("webdriver.chrome.driver", path + "linux/chromedriver");
            System.setProperty("phantomjs.binary.path", path + "linux/phantomjs");
        }

        log.info("Detected OS is '{}'", osName);
        log.info("Chrome driver path is    '{}'", System.getProperty("webdriver.chrome.driver"));
        log.info("PhantomJS driver path is '{}'", System.getProperty("phantomjs.binary.path"));
        log.info("IE driver path is        '{}'", System.getProperty("webdriver.ie.driver"));
    }

}
