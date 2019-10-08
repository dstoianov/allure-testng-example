package se.techinsight.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.xml.XmlSuite;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Funker on 15.07.2015.
 */
public class CustomReport implements IReporter {

    private static final Logger log = LoggerFactory.getLogger(CustomReport.class);

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
//        File dir = new File(System.getProperty("user.dir") + "/examples/" + outputDirectory);
//        dir.mkdir();

        // Second parameter of this method ISuite will contain all the suite executed.
        for (ISuite iSuite : suites) {
            // Get a map of result of a single suite at a time
            Map<String, ISuiteResult> results = iSuite.getResults();
            // Get the key of the result map
            Set<String> keys = results.keySet();
            // Go to each map value one by one
            for (String key : keys) {
                // The Context object of current result
                ITestContext context = results.get(key).getTestContext();
                // Print Suite detail in Console
                log.info("Suite Name -> " + context.getName()
                        + "\n::Report output Directory -> " + context.getOutputDirectory()
                        + "\n::Suite Name -> " + context.getSuite().getName()
                        + "\n::Start Date Time for execution -> " + context.getStartDate()
                        + "\n::End   Date Time for execution -> " + context.getEndDate());

                // Get Map for only failed test cases
                IResultMap resultMap = context.getFailedTests();
                // Get method detail of failed test cases
                Collection<ITestNGMethod> failedMethods = resultMap.getAllMethods();
                if (!failedMethods.isEmpty()) {
                    log.info("--------FAILED TEST(S) CASE '{}'---------", failedMethods.size());
                    // Loop one by one in all failed methods
                    for (ITestNGMethod iTestNGMethod : failedMethods) {
                        StringBuilder sb = new StringBuilder();
                        // Print failed test cases detail
                        sb.append("TESTCASE NAME -> ").append(iTestNGMethod.getMethodName()); //iTestNGMethod.getTestClass().getName()
                        if (iTestNGMethod.getDescription() != null) {
                            sb.append("\n\t:Description -> ").append(iTestNGMethod.getDescription());
                        }
                        if (iTestNGMethod.getPriority() != 0) {
                            sb.append("\n\t:Priority -> ").append(iTestNGMethod.getPriority());
                        }
//                        sb.append(":Date->" + new Date(iTestNGMethod.getDate()));
                        log.info(sb.toString());
                    }
                }
            }
        }
    }
}
