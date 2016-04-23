package change.test.name;

import bean.TestParameters;
import org.testng.ITest;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

/**
 * Created by Funker on 21.04.2016.
 */
public class ChangeTestNameBaseTest implements ITest {

    protected String testName;

    @Override
    public String getTestName() {
        return testName;
    }

    @BeforeMethod(alwaysRun = true)
    public void initPages(Method method, Object[] parameters) {
        testName = method.getName();
        if (parameters.length > 0) {
            for (Object parameter : parameters) {
                if (parameter instanceof TestParameters) {
                    testName = String.format("%s_%s", method.getName(), ((TestParameters) parameter).getCountry());
                }
            }
        }
    }
}
