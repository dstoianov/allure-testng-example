package se.techinsight.listeners;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.TreeSet;

@Slf4j
public class OnFailure extends TestListenerAdapter {

    public static WebDriver driver;

    @Step("onFailure screenshot attach")
    @Override
    public void onTestFailure(ITestResult tr) {
        attachWindowScreenshot();
        if (driver != null) {
            attachBrowserScreenshot();
        } else {
            log.error("driver is null, can't create 'Browser window screenshot'");
        }
        attachSystemProperties();
    }

    @Attachment(value = "Full window screenshot")
    private byte[] attachWindowScreenshot() {
        log.info("Create screenshot by Java Robot");
        return captureScreenShot();
    }

    @Attachment(value = "Browser window screenshot", type = "image/png")
    private byte[] attachBrowserScreenshot() {
        log.info("Create screenshot by webdriver");
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    private byte[] captureScreenShot() {
        try {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            log.info("Detecting screen size '{}'", screenSize);
            BufferedImage image = new Robot().createScreenCapture(new Rectangle(screenSize));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.setUseCache(false);
            ImageIO.write(image, "png", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            log.info("image size is '{}'", imageInByte.length);
            baos.close();
            // TODO: avoiding attach black screen, if image size more then 30k bytes
            if (imageInByte.length / 1024 > 30) {
                return imageInByte;
            } else {
                log.info("Something goes wrong, image does not converted");
                return "The size of screenshot is too small, it may be because black screen.".getBytes();
            }
        } catch (IOException | AWTException e) {
            e.printStackTrace();
        }
        return "Attachment Content Empty, can't create screenshot".getBytes();
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
