package browser;

import com.company.Behaviors;
import driver.BrowserBase;
import lombok.extern.slf4j.Slf4j;
import my.company.steps.WebDriverSteps;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import static org.testng.Assert.fail;

@Slf4j
@Features(Behaviors.Feature.BROWSER)
@Stories(Behaviors.Story.BROWSER)
public class ChromeBrowserTest extends BrowserBase {

    @BeforeMethod
    public void setUp() {
        log.info(">>> Start chrome driver >>>>");
        DRIVER_MAP.putIfAbsent(Thread.currentThread().getId(), new ChromeDriver());
        steps = new WebDriverSteps(getDriver());
    }

    @Description("chrome test")
    @Test
    public void searchByChromeTest() {
        steps.openMainPage();
        steps.search("Yandex QATools");
        fail();
        steps.makeError();
//        steps.makeScreenshot();
    }


}
