package data.provider;

import bean.TestParameters;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by Funker on 20.04.2016.
 */
public class ChangeNameTest /*implements ITest*/ {

//    private String testName;
//
//    @Override
//    public String getTestName() {
//        return testName;
//    }
//
//    @BeforeMethod(alwaysRun = true)
//    public void testData(Method method, Object[] testData) {
//        String testCase = "";
//        if (testData != null && testData.length > 0) {
//            TestParameters testParams = null;
//            //Check if test method has actually received required parameters
//            for (Object testParameter : testData) {
//                if (testParameter instanceof TestParameters) {
//                    testParams = (TestParameters) testParameter;
//                    break;
//                }
//            }
//            if (testParams != null) {
//                testCase = testParams.getCountry();
//            }
//        }
//        this.testName = String.format("%s(%s)", method.getName(), testCase);
//    }


    @Test(groups = {"change_name"}, dataProvider = "basicData", dataProviderClass = TestDataProvider.class)
    public void testSample1(TestParameters data) {
        assertThat(data.getCountry(), is(not(nullValue())));
        assertThat(data.getData(), is(not(nullValue())));
    }


}
