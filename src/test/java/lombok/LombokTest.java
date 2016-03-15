package lombok;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

/**
 * Created by denys.stoianov on 2016-03-15.
 */
@Slf4j
public class LombokTest {


    @Test
    public void testName() throws Exception {
        log.info("Hello!");
    }
}
