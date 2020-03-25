package se.techinsight.test.browser;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.techinsight.Behaviors;
import se.techinsight.steps.WebDriverSteps;

@Slf4j
@Feature(Behaviors.Feature.BROWSER)
@Story(Behaviors.Story.BROWSER)
public class FireFoxBrowserTest extends BaseTest {

    @BeforeMethod
    public void beforeMethod() {
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
