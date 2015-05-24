package my.company;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.TestCaseId;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.fail;


/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 24.11.13
 */
public class SimpleTest {

    @Test
    public void simpleIncorrect1Test() {
        assertThat("Incorrect", 2, is(3));
    }

    @Step
    public void checkThat2is2() {
        assertThat(2, is(2));
    }

    @Test
    public void simpleTestWithSteps() throws Exception {
        checkThat2is2();
    }

    @Attachment
    public String makeAttach() {
        return "yeah, 2 is 2";
    }

    @Test
    public void simpleTestWithAttachments() throws Exception {
        assertThat(2, is(2));
        makeAttach();
    }

    @Test
    public void failedTest() {
        fail();
    }

    @Test(dependsOnMethods = "failedTest")
    public void skippedByDependencyTest() {
    }

    @Test(enabled = false, description = "This is pending test, enabled = false")
    public void skippedTest() throws Exception {
    }

    @DataProvider
    public Object[][] dataProvider() {
        return new Object[][]{
                {"first"},
                {"second"},
                {"third"}
        };
    }

    @Test(dataProvider = "dataProvider")
    public void parametrizedTest(String parameter) {
        assertThat(parameter, is("second"));
    }

    @Test
    public void simpleIncorrect2Test() {
        assertThat("Incorrect", 2, is(3));
    }

    @TestCaseId("id-1335")
    @Test
    public void simpleIncorrect3Test() {
        assertThat("Incorrect", 2, is(3));
    }

    @Test(enabled = false, description = "This is pending test, enabled = false")
    public void pendingTest() {
    }

    @Test
    public void csvAttachmentTest() throws Exception {
        saveCsvAttachment();
    }

    @Attachment(value = "Sample csv attachment", type = "text/csv")
    public byte[] saveCsvAttachment() throws URISyntaxException, IOException {
        URL resource = getClass().getClassLoader().getResource("sample.csv");
        if (resource == null) {
            fail("Couldn't find resource 'sample.csv'");
        }
        return Files.readAllBytes(Paths.get(resource.toURI()));
    }

}

