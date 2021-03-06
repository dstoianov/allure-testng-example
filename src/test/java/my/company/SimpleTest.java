package my.company;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;

import java.io.IOException;
import java.io.InputStream;

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
    public void simpleTestWithSteps() {
        checkThat2is2();
    }

    @Attachment
    public String makeAttach() {
        return "yeah, 2 is 2";
    }

    @Test
    public void simpleTestWithAttachments() {
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
    public void skippedTest() {
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
    public void csvAttachmentTest() throws IOException {
        saveCsvAttachment();
    }

    @Attachment(value = "Sample csv attachment", type = "text/csv")
    public byte[] saveCsvAttachment() throws IOException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("sample.csv")) {
            return IOUtils.toByteArray(is);
        } catch (NullPointerException e) {
            throw new RuntimeException("Couldn't find resource 'sample.csv'");
        }
//        throw new RuntimeException("Couldn't find resource 'sample.csv'");
//        URL resource = getClass().getClassLoader().getResource("sample.csv");
//        if (resource == null) {
//            fail("Couldn't find resource 'sample.csv'");
//        }
//        InputStream is = resource.openStream();
//        return IOUtils.toByteArray(is);
    }

    @Test
    public void htmlAttachmentTest() {
        saveHTMLAttachment();
    }

    @Attachment(value = "Sample HTML attachment", type = "text/html")
    public byte[] saveHTMLAttachment() {
        String s = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<h3>My First Heading</h3>\n" +
                " <a href=\"http://www.w3schools.com\">This is a link</a> \n" +
                "\n" +
                "<table style=\"width:100%\">\n" +
                "  <tr>\n" +
                "    <td>Jill</td><td>Smith</td><td>50</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>Eve</td><td>Jackson</td><td>94</td>\n" +
                "  </tr>\n" +
                "</table>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
        return s.getBytes();
    }


    @Issues({@Issue("ALR-123"), @Issue("ALR-456"), @Issue("ALR-789")})
    @TestCaseId("TMS-123")
    @Test(dataProvider = "dataProvider")
    public void parametrizedSingleTest(@Parameter int parameter) {
        assertThat(parameter, is(2));
    }

    @DataProvider
    public Object[][] anotherDataProvider() {
        return new Object[][]{
                {"Long-long parameter value", 1, 2},
                {"Even longer parameter value than long-long parameter value", 2, 3},
                {"", 3, 4}
        };
    }

    @Issues({@Issue("ALR-123"), @Issue("ALR-456"), @Issue("ALR-789")})
    @TestCaseId("TMS-123")
    @Test(dataProvider = "anotherDataProvider")
    public void parametrizedMultipleTest(@Parameter String parameter1, @Parameter("Third Parameter Name") int parameter2, int parameters3) {
        assertThat(parameter2, is(2));
    }

}

