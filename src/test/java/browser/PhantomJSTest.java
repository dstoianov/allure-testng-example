package browser;

import com.company.Behaviors;
import driver.BrowserBase;
import lombok.extern.slf4j.Slf4j;
import my.company.steps.WebDriverSteps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import java.util.concurrent.TimeUnit;

@Slf4j
@Features(Behaviors.Feature.BROWSER)
@Stories(Behaviors.Story.BROWSER)
public class PhantomJSTest extends BrowserBase {


    @BeforeMethod
    public void setUp() {
        log.info(">>> Start PhantomJS driver >>>>");
        DRIVER_MAP.putIfAbsent(Thread.currentThread().getId(), getPhantomJSDeriver());
        log.info("driver is OK");
        steps = new WebDriverSteps(getDriver());
    }

    @Description("phantomjs test")
    @Test
    public void searchByPhantomJSTest() {
        steps.openMainPage();
        steps.search("Yandex QATools");
        log.info(">>> Search by test in browser windows ");
        steps.makeScreenshot();
        steps.makeError();
    }

    private WebDriver getPhantomJSDeriver() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability("takesScreenshot", true);
        WebDriver driver = new PhantomJSDriver(caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

}
