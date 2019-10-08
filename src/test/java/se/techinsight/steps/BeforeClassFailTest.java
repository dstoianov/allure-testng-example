package se.techinsight.steps;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Boris Serdyuk just-boris@yandex-team.ru
 *         Date: 28.04.14
 */
public class BeforeClassFailTest {

    @BeforeClass
    public static void setUp() {
        throw new RuntimeException("bye-bye");
    }

    @Test
    public void testTest() {

    }
}
