package browser;

import com.company.Behaviors;
import lombok.extern.slf4j.Slf4j;
import my.company.steps.WebDriverSteps;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Slf4j
@Features(Behaviors.Feature.BROWSER)
@Stories(Behaviors.Story.BROWSER)
public class ChromeBrowserTest extends BrowserBase {

    @BeforeMethod
    public void setUp() {
        log.info(">>> Start chrome driver >>>>");
        ChromeDriver driver = new ChromeDriver();
        steps = new WebDriverSteps(driver);
    }

    @Description("chrome test")
    @Test
    public void searchByChromeTest() {
        steps.openMainPage();
        steps.search("Yandex QATools");
        steps.makeScreenshot();
    }


}
