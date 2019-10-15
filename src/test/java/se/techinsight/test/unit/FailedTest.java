package se.techinsight.test.unit;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FailedTest {

    @Description("Hohoho Description")
    @Test
    public void testName() {
        assertThat(false, is(true));
    }
}
