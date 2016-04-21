package data.provider;

import bean.TestParameters;
import org.testng.annotations.DataProvider;

/**
 * Created by Funker on 21.04.2016.
 */
public class TestDataProvider {

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
}
