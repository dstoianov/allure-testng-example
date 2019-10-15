package se.techinsight.listeners;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import se.techinsight.browser.BrowserHolder;

import java.util.Properties;
import java.util.TreeSet;

@Slf4j
public class ScreenshotListener extends TestListenerAdapter {

    @Step("onFailure screenshot attach")
    @Override
    public void onTestFailure(ITestResult tr) {
//        WebDriver webDriver = ((BaseTest) tr.getInstance()).getWebDriver();

        if (BrowserHolder.getDriver() != null) {
            attachScreenshot(BrowserHolder.getDriver());
            attachCurrentUrl(BrowserHolder.getDriver());
            attachPageSource(BrowserHolder.getDriver());
        } else {
            log.error("driver is null, can't create 'Browser window screenshot'");
        }
//        attachSystemProperties();
        attachSystemPropertiesCsv();
    }

    @Attachment(value = "Browser window screenshot", type = "image/png")
    private byte[] attachScreenshot(WebDriver driver) {
        log.info("Create screenshot by webdriver");
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Current url", type = "text/uri-list")
    private byte[] attachCurrentUrl(WebDriver webDriver) {
        log.info("Getting current page URL by Webdriver");
        return webDriver.getCurrentUrl().getBytes();
    }

    @Attachment(value = "Page source", type = "text/html")
    private byte[] attachPageSource(WebDriver webDriver) {
        log.info("Getting current page URL by Webdriver");
        return webDriver.getPageSource().getBytes();
    }


    @Attachment(value = "System Environment", type = "text/plain")
    private byte[] attachSystemProperties() {
        Properties props = System.getProperties();
        StringBuilder result = new StringBuilder();
        for (String prop : new TreeSet<>(props.stringPropertyNames())) {
            if (prop.startsWith("surefire.test.class.path")
                    || prop.startsWith("java.class.path")) continue;
            result.append(prop)
                    .append(" = ")
                    .append(System.getProperty(prop))
                    .append("\n");
        }
        return result.toString().getBytes();
    }

    @Attachment(value = "System Environment", type = "text/csv")
    private byte[] attachSystemPropertiesCsv() {
        Properties props = System.getProperties();
        StringBuilder result = new StringBuilder();
        for (String prop : new TreeSet<>(props.stringPropertyNames())) {
            if (prop.startsWith("surefire.test.class.path")
                || prop.startsWith("java.class.path")) {
                continue;
            }
            result.append(prop)
                .append(",")
                .append(System.getProperty(prop))
                .append("\n");
        }
        return result.toString().getBytes();
    }

}
