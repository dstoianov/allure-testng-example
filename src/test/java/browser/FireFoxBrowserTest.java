package browser;

import my.company.steps.WebDriverSteps;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;

@Features("Browser tests")
public class FireFoxBrowserTest extends BrowserBase {


    @BeforeMethod
    public void setUp() {
        steps = new WebDriverSteps(new FirefoxDriver());
    }


    @Description("firefox test")
    @Test
    public void searchTest() {
        steps.openMainPage();
        steps.search("Yandex QATools");
        steps.makeScreenshot();
    }

}
