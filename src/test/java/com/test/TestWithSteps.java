package com.test;

import com.company.MyClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

/**
 * Created by Funker on 17.11.2014, 0:43.
 */
public class TestWithSteps {


    @Title("test with steps 2, nested steps")
    @Test
    public void sampleTest() throws Exception {
        step1();
        step2();
        step3();
    }

    @Step("description for step 1")
    public void step1() {
    }

    @Step("description for step 2")
    public void step2() {
        step3();
    }

    @Step("description for step 3")
    public void step3() {
    }


    @Test()
    @Step("Test step")
    public void testEmailGenerator() {

        final MyClass obj = new MyClass();
        final String email = obj.generate();

        assertThat(email, notNullValue());
        assertThat(email, is("feedback@yoursite.com"));
    }
}
