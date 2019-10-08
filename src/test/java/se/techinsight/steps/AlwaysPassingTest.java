package se.techinsight.steps;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import se.techinsight.Behaviors;

import static org.testng.Assert.assertTrue;

/**
 * @author Boris Serdyuk just-boris@yandex-team.ru
 *         Date: 12/12/13
 */
@Feature(Behaviors.Story.title)
@Story(Behaviors.Story.SUCCESS_STORY)
public class AlwaysPassingTest {

    @Test(priority = 1)
    public void testOne() {
        assertTrue(false);
    }

    @Test(priority = 3)
    public void testTwo() {
        assertTrue(true);
    }

    @Test(priority = 2, description = "some description")
    public void testThree() {
        assertTrue(false);
    }

    @Test(priority = 8)
    public void testFour() {
        assertTrue(true);
    }

    @Story(Behaviors.Story.FAILED_STORY)
    @Test(description = "description for this failed test")
    public void testFifeFailed() {
        assertTrue(false);
    }

}
