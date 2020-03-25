package se.techinsight.test.browser;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import se.techinsight.browser.BrowserHolder;
import se.techinsight.steps.WebDriverSteps;

/**
 * Created by Funker on 30.05.2016.
 */
@Slf4j
public class BaseTest {

    protected WebDriverSteps steps;

    WebDriver getDriver() {
        return BrowserHolder.getDriver();
    }

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        BrowserHolder.init();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        BrowserHolder.quit();
    }
}
