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
public class OnFailure extends TestListenerAdapter {

    @Step("onFailure screenshot attach")
    @Override
    public void onTestFailure(ITestResult tr) {
        if (BrowserHolder.getDriver() != null) {
            attachBrowserScreenshot(BrowserHolder.getDriver());
        } else {
            log.error("driver is null, can't create 'Browser window screenshot'");
        }
        attachSystemProperties();
    }

    @Attachment(value = "Browser window screenshot", type = "image/png")
    private byte[] attachBrowserScreenshot(WebDriver driver) {
        log.info("Create screenshot by webdriver");
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
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

}
