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
@Features(Behaviors.SimpleStories.title)
@Stories(Behaviors.SimpleStories.SUCCESS_STORY)
public class AlwaysPassingTest {

    @Test
    public void testOne() throws Exception {
        assertTrue(true);
    }

    @Test
    public void testTwo() throws Exception {
        assertTrue(true);
    }

    @Test
    public void testThree() throws Exception {
        assertTrue(true);
    }

    @Test
    public void testFour() throws Exception {
        assertTrue(true);
    }

    @Stories(Behaviors.SimpleStories.FAILED_STORY)
    @Test
    public void testFifeFailed() throws Exception {
        assertTrue(false);
    }

}
