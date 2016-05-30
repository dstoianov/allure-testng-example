package my.company;

import com.company.Behaviors;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import static org.junit.Assert.assertTrue;

/**
 * @author Boris Serdyuk just-boris@yandex-team.ru
 *         Date: 12/12/13
 */
@Features(Behaviors.Story.title)
@Stories(Behaviors.Story.SUCCESS_STORY)
public class AlwaysPassingTest {

    @Test(priority = 1)
    public void testOne() throws Exception {
        assertTrue(false);
    }

    @Test(priority = 3)
    public void testTwo() throws Exception {
        assertTrue(true);
    }

    @Test(priority = 2, description = "some description")
    public void testThree() throws Exception {
        assertTrue(false);
    }

    @Test(priority = 8)
    public void testFour() throws Exception {
        assertTrue(true);
    }

    @Stories(Behaviors.Story.FAILED_STORY)
    @Test(description = "description for this failed test")
    public void testFifeFailed() throws Exception {
        assertTrue(false);
    }

}
