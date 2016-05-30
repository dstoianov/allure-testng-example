package com.test;

import com.company.Behaviors;
import com.company.MyClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

/**
 * Created by Funker on 17.11.2014, 0:43.
 */

@Features("Steps Feature")
@Stories(Behaviors.Story.SIMPLE_STORY)
public class TestWithSteps {

    @Title("Test with 3 steps and 1 nested step")
    @Test
    public void sampleTest() throws Exception {
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("Sleep for '%s' ms", millis));
    }

}
