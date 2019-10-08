package se.techinsight.test.unit.name;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import se.techinsight.bean.TestParameters;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

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

    @Test(groups = {"bonding", "strong_ties"}, dataProvider = "basicData")
    public void testSample1(TestParameters data) {
        assertThat(data.getCountry(), is(not(nullValue())));
        assertThat(data.getData(), is(not(nullValue())));
    }
}
