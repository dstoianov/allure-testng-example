package browser;

import com.company.Behaviors;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.techinsight.steps.WebDriverSteps;

import static org.testng.Assert.fail;

@Slf4j
@Feature(Behaviors.Feature.BROWSER)
@Story(Behaviors.Story.BROWSER)
public class ChromeBrowserTest extends BrowserBaseTest {

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        DRIVER_MAP.putIfAbsent(Thread.currentThread().getId(), new ChromeDriver());
        steps = new WebDriverSteps(getDriver());
    }

    @Description("chrome test")
    @Test
    public void searchByChromeTest() {
        steps.openMainPage();
        steps.search("Yandex QATools");
        fail("make test failed");
        steps.makeError();
//        steps.makeScreenshot();
    }


}
