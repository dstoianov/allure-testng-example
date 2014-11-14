package my.company.steps;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 08.09.14
 */
public class OnFailure extends TestListenerAdapter {

    @Step("Hi, I'm step in your testng listener")
    @Override
    public void onTestFailure(ITestResult tr) {
        createAttachment();
    }

//    @Attachment("Hi, I'm attachment in your testng listener")
//    public String createAttachment() {
//        return "My own attachment body!";
//    }

    @Attachment(value = "PNG Attachment", type = "image/png")
    public byte[] createAttachment() {
        return captureScreenShot();
    }


    private byte[] captureScreenShot() {
        try {
            BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            ImageIO.write(image, "png", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (IOException | AWTException e) {
            e.printStackTrace();
        }
        return "Attachment Content Empty".getBytes();
    }

}
