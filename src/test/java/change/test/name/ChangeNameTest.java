package change.test.name;

import bean.TestParameters;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by Funker on 20.04.2016.
 */
public class ChangeNameTest extends ChangeTestNameBaseTest {

    @DataProvider(name = "basicData")
    public static Object[][] getTestData() {
        Object[][] result = new Object[][]{
                {new TestParameters("Germany", "Sample test 1")},
                {new TestParameters("Austria", "Sample test 2")},
                {new TestParameters("Norwegian", null)},
                {new TestParameters("France", "Sample test 4")},
                {new TestParameters("Netherlands", "Sample test 5")}
        };
        return result;
    }

    @Test(groups = {"change_name"}, dataProvider = "basicData")
    public void testSample1(TestParameters data) {
        assertThat(data.getCountry(), is(not(nullValue())));
        assertThat(data.getData(), is(not(nullValue())));
    }

}
