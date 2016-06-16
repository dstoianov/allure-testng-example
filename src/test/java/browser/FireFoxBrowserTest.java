package browser;

import com.company.Behaviors;
import driver.BrowserBase;
import lombok.extern.slf4j.Slf4j;
import my.company.steps.WebDriverSteps;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Slf4j
@Features(Behaviors.Feature.BROWSER)
@Stories(Behaviors.Story.BROWSER)
public class FireFoxBrowserTest extends BrowserBase {


    @BeforeMethod
    public void setUp() {
        log.info(">>> Start firefox driver >>>>");
        DRIVER_MAP.putIfAbsent(Thread.currentThread().getId(), new FirefoxDriver());
        steps = new WebDriverSteps(getDriver());
    }


    @Description("firefox test")
//    @Test
    public void searchByFirefoxTest() {
        steps.openMainPage();
        steps.search("Yandex QATools");
        steps.makeError();
//        steps.makeScreenshot();
    }

}
