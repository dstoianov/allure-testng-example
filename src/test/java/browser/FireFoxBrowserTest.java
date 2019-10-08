package browser;

import com.company.Behaviors;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.techinsight.steps.WebDriverSteps;


@Slf4j
@Feature(Behaviors.Feature.BROWSER)
@Story(Behaviors.Story.BROWSER)
public class FireFoxBrowserTest extends BrowserBaseTest {


    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        DRIVER_MAP.putIfAbsent(Thread.currentThread().getId(), new FirefoxDriver());
        steps = new WebDriverSteps(getDriver());
    }


    @Description("firefox test")
    @Test
    public void searchByFirefoxTest() {
        steps.openMainPage();
        steps.search("Yandex QATools");
        steps.makeError();
        steps.makeScreenshot();
    }

}
