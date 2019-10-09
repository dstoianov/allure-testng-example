package se.techinsight.test.unit;

import io.qameta.allure.Flaky;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Slf4j
public class RetryTest {

    @Flaky
    @Test
    public void testRetryListenerSetToSkipp() {
        int i = new Random().nextInt(3);
        log.info("New Run, test said: Random int is: '{}'", i);
        assertThat(i, is(1));
    }
}
