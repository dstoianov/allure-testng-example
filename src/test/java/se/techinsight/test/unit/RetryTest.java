package se.techinsight.test.unit;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Slf4j
public class RetryTest {

    @Test
    public void testRetryListenerSetToSkipp() {
        int i = new Random().nextInt(5);
        log.info("New Run, test said: Random int is: '{}'", i);
        assertThat(i, is(3));
    }
}
