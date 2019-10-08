package se.techinsight.test.browser;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Issues;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Stories;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import se.techinsight.Behaviors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.fail;

/**
 * Created by dstoianov on 2014-11-06, 6:20 PM
 */
@Feature(Behaviors.Feature.BROWSER)
public class FirstAllureTest extends BaseTest {

    String xmlAttachmnet =
        "<note><to>Tove</to><from>Jani</from><heading>Reminder</heading><body>Don't forget me this weekend!</body></note>";
    String jsonAttachmet = "{\n"
        +
        "    \"glossary\": {\n"
        +
        "        \"title\": \"example glossary\",\n"
        +
        "\t\t\"GlossDiv\": {\n"
        +
        "            \"title\": \"S\",\n"
        +
        "\t\t\t\"GlossList\": {\n"
        +
        "                \"GlossEntry\": {\n"
        +
        "                    \"ID\": \"SGML\",\n"
        +
        "\t\t\t\t\t\"SortAs\": \"SGML\",\n"
        +
        "\t\t\t\t\t\"GlossTerm\": \"Standard Generalized Markup Language\",\n"
        +
        "\t\t\t\t\t\"Acronym\": \"SGML\",\n"
        +
        "\t\t\t\t\t\"Abbrev\": \"ISO 8879:1986\",\n"
        +
        "\t\t\t\t\t\"GlossDef\": {\n"
        +
        "                        \"para\": \"A meta-markup language, used to create markup languages such as DocBook.\",\n"
        +
        "\t\t\t\t\t\t\"GlossSeeAlso\": [\"GML\", \"XML\"]\n"
        +
        "                    },\n"
        +
        "\t\t\t\t\t\"GlossSee\": \"markup\"\n"
        +
        "                }\n"
        +
        "            }\n"
        +
        "        }\n"
        +
        "    }\n"
        +
        "}";

    @BeforeClass
    public void setUp() {
        super.setUpPath();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        super.tearDown();
    }

    @Issue("JIRA-254")
    @Description("some description for this test")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void testName1() {
        getDriver().get("http://google.com.ua");
        makeScreenShot();
        fail();
    }

    @Issues({
        @Issue("JIRA-1"),
        @Issue("JIRA-2")
    })
    @TmsLink("JIRA-3254")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "some description from test")
    public void testName2() {

        Capabilities cap = ((RemoteWebDriver) getDriver()).getCapabilities();
        String browser = cap.getBrowserName();
        getDriver().get("http://google.com.ua");
    }

    @Description("This is the Big Title for test")
    @Severity(SeverityLevel.CRITICAL)
    @Stories({@Story("dddd"), @Story("ddddd")})
    @Test
    public void testName3() {
        getDriver().get("https://www.google.com/ncr");
        makeScreenShot();
    }

    @Description("Test that should fail")
    @Severity(SeverityLevel.TRIVIAL)
    @Test
    public void failedTest() {
        fail();
    }

    @Description("default.description")
    @Story("default.story")
    @Issue("JIRA-321")
    @Test
    public void testName4() {
        getDriver().get("http://google.co.uk");

        jsonAttach();
        xmlAttach();
        makeScreenShot();
        fail("some message for fail");
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Test with long assertion text")
    @Issue("JIRA-254")
    @TmsLink("JIRA-2547")
    @Test
    public void testName5() {
    }

    @Description("I hate descriptions! See the title!")
    @Test
    public void testName6() {
        assertThat(4, is(2 + 2));
    }

    @Test(description = "description from TestNG annotation")
    public void testName7() {
        assertThat(4, is(2 + 2));
    }

    @Attachment(value = "XML Attachment", type = "application/xml")
    public byte[] xmlAttach() {
        return xmlAttachmnet.getBytes();
    }

    @Attachment(value = "JSON Attachment", type = "application/json")
    public byte[] jsonAttach() {
        return jsonAttachmet.getBytes();
    }

    @Attachment(value = "PNG wd Attachment", type = "image/png")
    public byte[] makeScreenShot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
