package se.techinsight.test.unit;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import se.techinsight.Behaviors;
import se.techinsight.MyClass;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

/**
 * Created by Funker on 17.11.2014, 0:43.
 */

@Feature("Steps Feature")
@Story(Behaviors.Story.SIMPLE_STORY)
public class TestWithSteps {

    private static final Logger log = LoggerFactory.getLogger(TestWithSteps.class);

    @Description("Test with 3 steps and 1 nested step")
    @Test
    public void sampleTest() {
        step1();
        step2();
        step3();
    }

    @Step("description for step 1")
    public void step1() {
        sleep(100);
    }

    @Step("description for step 2")
    public void step2() {
        sleep(200);
        step3();
    }

    @Step("description for step 3")
    public void step3() {
        sleep(300);
    }

    @Step("Test step")
    @Test
    public void testEmailGenerator() {

        final MyClass obj = new MyClass();
        final String email = obj.generate();

        assertThat(email, notNullValue());
        assertThat(email, is("feedback@yoursite.com"));
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
            System.out.println(String.format("sleep for '%s' ms", millis));
        } catch (InterruptedException e) {
            log.error("Interrupted Exception", e);
        }
    }

}
