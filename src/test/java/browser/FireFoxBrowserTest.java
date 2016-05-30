package browser;

import com.company.Behaviors;
import my.company.steps.WebDriverSteps;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Features(Behaviors.Feature.BROWSER)
@Stories(Behaviors.Story.BROWSER)
public class FireFoxBrowserTest extends BrowserBase {


    @BeforeMethod
    public void setUp() {
        steps = new WebDriverSteps(new FirefoxDriver());
    }


    @Description("firefox test")
    @Test
    public void searchByFirefoxTest() {
        steps.openMainPage();
        steps.search("Yandex QATools");
        steps.makeScreenshot();
    }

}
