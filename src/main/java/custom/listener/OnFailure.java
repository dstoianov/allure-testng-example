package custom.listener;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

public class OnFailure extends TestListenerAdapter {

    public static WebDriver driver;

    @Step("onFailure screenshot attach")
    @Override
    public void onTestFailure(ITestResult tr) {
        if (driver != null) {
            attachBrowserScreenshot();
        } else {
            System.err.println("driver is null, can't create 'Browser window screenshot'");
        }
        attachWindowScreenshot();
        attachSystemProperties();
    }

    @Attachment(value = "Full window screenshot")
    private byte[] attachWindowScreenshot() {
        return captureScreenShot();
    }

    @Attachment(value = "Browser window screenshot", type = "image/png")
    private byte[] attachBrowserScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    private byte[] captureScreenShot() {
        try {
            BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            // TODO: avoiding black screen, if image size more then 30k bytes
            if (imageInByte.length / 1024 > 30) {
                return imageInByte;
            } else {
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
        for (String prop : props.stringPropertyNames()) {
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
