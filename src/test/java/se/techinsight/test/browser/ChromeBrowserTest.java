package se.techinsight.test.browser;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.techinsight.Behaviors;
import se.techinsight.steps.WebDriverSteps;

import static org.testng.Assert.fail;

@Slf4j
@Feature(Behaviors.Feature.BROWSER)
@Story(Behaviors.Story.BROWSER)
public class ChromeBrowserTest extends BaseTest {

    @BeforeMethod
    public void setUp() {
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
