package com.test;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.config.AllureConfig;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.io.File;
import java.sql.Driver;
import java.util.Properties;

import static org.testng.AssertJUnit.fail;

/**
 * Created by dstoianov on 2014-11-06, 6:20 PM
 * E-mail:
 */
public class FirstAllureTest {

    @Parameter("My Param")
    private String myParameter;

    private WebDriver driver;

    @BeforeClass
    public void setUp() {

        String property = System.getProperty("phantomjs.binary.path");
        String property2 = System.getProperty("webdriver.chrome.driver");
        String property3 = System.getProperty("webdriver.ie.driver");
//        driver = new FirefoxDriver();
        driver = new PhantomJSDriver();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Issue("JIRA-254")
    @Description("some description for this test")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void testName1() throws Exception {
        driver.get("http://google.com.ua");
        makeScreenshot();
    }


    @Issues({@Issue("JIRA-1"), @Issue("JIRA-2")})
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "some description from test")
    public void testName2() throws Exception {
        myParameter = "papa";
        myParameter = "mapa";
        myParameter = "mama";
        driver.get("http://google.com.ua");

        String chromeDdriver = System.getProperty("webdriver.chrome.driver");

        String direc = System.getProperty("allure.results.directory");
        String issue = System.getProperty("my.test.var");
        System.out.println("my.test.var            : ----> " + issue);
        System.out.println("allure.results.directory : ----> " + direc);

        String property1 = System.getProperty("project.version");

        File defaultResultsDirectory = AllureConfig.getDefaultResultsDirectory();
        AllureConfig allureConfig = new AllureConfig();
        String s = allureConfig.getTestSuiteFileSuffix();
    }


    @Title("This is the Big Title for test")
    @Severity(SeverityLevel.CRITICAL)
    @Features("My Feature")
    @Stories({"Story1", "Story2"})
    @Test
    public void testName3() throws Exception {
        driver.get("http://google.co.uk");

    }

    @Title("Test that should fail")
    @Severity(SeverityLevel.TRIVIAL)
    @Test
    public void failedTest() {
        fail();
    }


    @Test
    public void testName4() throws Exception {
        driver.get("http://google.co.uk");

        fail();
    }


    @Attachment(type = "image/png")
    public byte[] makeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
