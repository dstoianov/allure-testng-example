package my.company;

import my.company.steps.WebDriverSteps;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 24.11.13
 */
@Features("Browser test Yandex")
public class SearchTest {

    private WebDriverSteps steps;

    @BeforeMethod
    public void setUp() throws Exception {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().contains("win")) {
            System.setProperty("phantomjs.binary.path", "./src/test/resources/drivers/phantomjs.exe");
        }
//        steps = new WebDriverSteps(new PhantomJSDriver());
        steps = new WebDriverSteps(new FirefoxDriver());
    }


    @Test
    public void searchTest() throws Exception {
        steps.openMainPage();
        steps.search("Yandex QATools");
        steps.makeScreenshot();
        steps.quit();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (steps != null) {
            steps.quit();
        }
    }
}
