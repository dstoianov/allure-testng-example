package com.test;


import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.config.AllureConfig;
import ru.yandex.qatools.allure.events.AddParameterEvent;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.testng.AssertJUnit.fail;

/**
 * Created by dstoianov on 2014-11-06, 6:20 PM
 * E-mail:
 */
public class FirstAllureTest {

    String s = "<note><to>Tove</to><from>Jani</from><heading>Reminder</heading><body>Don't forget me this weekend!</body></note>";
    String json = "{\n" +
            "    \"glossary\": {\n" +
            "        \"title\": \"example glossary\",\n" +
            "\t\t\"GlossDiv\": {\n" +
            "            \"title\": \"S\",\n" +
            "\t\t\t\"GlossList\": {\n" +
            "                \"GlossEntry\": {\n" +
            "                    \"ID\": \"SGML\",\n" +
            "\t\t\t\t\t\"SortAs\": \"SGML\",\n" +
            "\t\t\t\t\t\"GlossTerm\": \"Standard Generalized Markup Language\",\n" +
            "\t\t\t\t\t\"Acronym\": \"SGML\",\n" +
            "\t\t\t\t\t\"Abbrev\": \"ISO 8879:1986\",\n" +
            "\t\t\t\t\t\"GlossDef\": {\n" +
            "                        \"para\": \"A meta-markup language, used to create markup languages such as DocBook.\",\n" +
            "\t\t\t\t\t\t\"GlossSeeAlso\": [\"GML\", \"XML\"]\n" +
            "                    },\n" +
            "\t\t\t\t\t\"GlossSee\": \"markup\"\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "}";
    @Parameter("Browser Name")
    private String browser;
    @Parameter("Browser Version")
    private String version;
    @Parameter("Platform")
    private String platform;
    private WebDriver driver;

    @BeforeClass
    public void setUp() {

        String p1 = System.getProperty("phantomjs.binary.path");
        String p2 = System.getProperty("webdriver.chrome.driver");
        String p3 = System.getProperty("webdriver.ie.driver");
//        driver = new FirefoxDriver();
        driver = new PhantomJSDriver();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Issue("JIRA-254")
    @Description("some description for this test")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void testName1() throws Exception {
        driver.get("http://google.com.ua");

        fail();
    }

    @Issues({
            @Issue("JIRA-1"),
            @Issue("JIRA-2")
    })
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "some description from test")
    public void testName2() throws Exception {

        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();

        browser = cap.getBrowserName();
        version = cap.getVersion();
        platform = cap.getPlatform().toString();

        driver.get("http://google.com.ua");

        String chromeDdriver = System.getProperty("webdriver.chrome.driver");

        String issue = System.getProperty("my.test.var");
        System.out.println("my.test.var                   : ----> " + issue);
        String direc = System.getProperty("allure.issues.tracker.pattern");
        System.out.println("allure.issues.tracker.pattern : ----> " + direc);

        String property1 = System.getProperty("project.version");

        File defaultResultsDirectory = AllureConfig.getDefaultResultsDirectory();
        AllureConfig allureConfig = new AllureConfig();
        String s = allureConfig.getTestSuiteFileSuffix();
    }

    @Title("This is the Big Title for test")
    @Severity(SeverityLevel.CRITICAL)
    @Features("My Feature")
    @Stories({"Story1", "Story2"})
    @Test
    public void testName3() throws Exception {
        driver.get("http://google.co.uk");

    }

    @Title("Test that should fail")
    @Severity(SeverityLevel.TRIVIAL)
    @Test
    public void failedTest() {
        fail();
    }

    @Title("default.title")
    @Description("default.description")
    @Features("default.feature")
    @Stories("default.story")
    @Issue("JIRA-321")
    @Test
    public void testName4() throws Exception {
        driver.get("http://google.co.uk");

        jsonAttach();
        xmlAttach();
        makeScreenShot();
        fail();

    }

    @Severity(SeverityLevel.NORMAL)
    @Title("Test with long assertion text")
    @Issue("JIRA-254")
    @Test
    public void testName5() throws Exception {
        Allure.LIFECYCLE.fire(new AddParameterEvent("PATH", System.getenv("PATH")));
        Allure.LIFECYCLE.fire(new AddParameterEvent("BASEDIR", new File(".").getCanonicalPath()));
        Allure.LIFECYCLE.fire(new AddParameterEvent("OS_NAME", System.getProperty("os.name")));
        Allure.LIFECYCLE.fire(new AddParameterEvent("OS_VERSION", System.getProperty("os.version")));
        Allure.LIFECYCLE.fire(new AddParameterEvent("JAVA_VERSION", System.getProperty("java.version")));

        makeScreenShot();
    }

    @Title("Test what do nothing and always passed right, but have a very long title, over 100 symbols, seriously")
    @Description("I hate descriptions! See the title!")
    @Test
    public void testName6() throws Exception {
        assertThat(4, is(2 + 2));
    }

    @Test(description = "description of from testNg annotation")
    public void testName7() throws Exception {
        assertThat(4, is(2 + 2));
    }

    @Attachment(value = "XML Attachment", type = "text/xml")
    public byte[] xmlAttach() {
        return s.getBytes();
    }

    @Attachment(value = "JSON Attachment", type = "application/json")
    public byte[] jsonAttach() {
        return json.getBytes();
    }

    @Attachment(value = "PNG wd Attachment", type = "image/png")
    public byte[] makeScreenShot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

}
