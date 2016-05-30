package browser;

import my.company.steps.WebDriverSteps;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;


public class ChromeBrowserTest extends BrowserBase {

    @BeforeMethod
    public void setUp() {
        steps = new WebDriverSteps(new ChromeDriver());
    }

    @Description("chrome test")
    @Test
    public void searchTest() {
        steps.openMainPage();
        steps.search("Yandex QATools");
        steps.makeScreenshot();
    }


}
