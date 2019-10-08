package se.techinsight.listeners;


import bean.TestParameters;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.internal.BaseTestMethod;
import org.testng.internal.TestResult;

import java.lang.reflect.Field;

/**
 * Created by Funker on 21.04.2016.
 */
public class ChangeTestNameListener implements ITestListener {


    @Override
    public void onTestStart(ITestResult result) {
        setName(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {

    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }

    private void setName(ITestResult result) {
        try {
            Object[] parameters = result.getParameters();
            if (result.getParameters().length > 0) {
                for (Object parameter : parameters) {
                    if (parameter instanceof TestParameters) {
                        String country = ((TestParameters) parameter).getCountry();
                        Field method = TestResult.class.getDeclaredField("m_method");
                        method.setAccessible(true);
                        method.set(result, result.getMethod().clone());
                        Field methodName = BaseTestMethod.class.getDeclaredField("m_methodName");
                        methodName.setAccessible(true);
                        methodName.set(result.getMethod(), result.getMethod().getMethodName() + "_" + country);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
