package custom.listener;

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

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 08.09.14
 */
public class OnFailure extends TestListenerAdapter {

    @Step("Hi, I'm step in your TestNG listener")
    @Override
    public void onTestFailure(ITestResult tr) {
        createAttachment();
        getSystemProperties();
        createAttachment2();
    }

    @Attachment("Hi, I'm attachment in your testng listener")
    public String createAttachment2() {
        return "My own attachment body!";
    }

    @Attachment(value = "PNG full Attachment", type = "image/png")
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
        return "Attachment Content Empty, can't create screenshot".getBytes();
    }

    @Attachment(value = "System Environment", type = "text/plain")
    public byte[] getSystemProperties() {
        Properties props = System.getProperties();
        StringBuilder result = new StringBuilder();
        for (String prop : props.stringPropertyNames()) {
            result.append(prop)
                    .append(" = ")
                    .append(System.getProperty(prop))
                    .append("\n");
        }
        return result.toString().getBytes();
    }

}
