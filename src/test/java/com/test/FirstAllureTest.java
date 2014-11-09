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
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.sql.Driver;

/**
 * Created by dstoianov on 2014-11-06, 6:20 PM
 * E-mail: <dstoianov@plslogistics.com>
 */
public class FirstAllureTest {

   private WebDriver driver;

    @BeforeClass
    public void setUp(){
//        driver = new FirefoxDriver();
        driver = new PhantomJSDriver(new DesiredCapabilities());
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void testName1() throws Exception {
        driver.get("http://google.com.ua");
        makeScreenshot();
    }

    @Test
    public void testName2() throws Exception {
        driver.get("http://google.co.uk");
        makeScreenshot();
    }

    @Attachment(type = "image/png")
    public File makeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    }
}
