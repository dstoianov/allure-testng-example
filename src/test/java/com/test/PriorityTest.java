package com.test;

import com.company.Behaviors;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;


/**
 * Created by Funker on 11.03.2015.
 */

@Feature(Behaviors.Priority.title)
@Story(Behaviors.Priority.STORY)
public class PriorityTest {

    @Description("test with priority 3")
    @Test(priority = 3)
    public void testName0() throws Exception {
        System.out.println("Test name: " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Description("test with priority 2")
    @Test(priority = 2)
    public void testName1() throws Exception {
        System.out.println("Test name: " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Description("test with priority 1")
    @Test(priority = 1)
    public void testName2() throws Exception {
        System.out.println("Test name: " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Description("test with priority 2")
    @Test(priority = 2)
    public void testName3() throws Exception {
        System.out.println("Test name: " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Description("test with priority 1")
    @Test(priority = 1)
    public void testName4() throws Exception {
        System.out.println("Test name: " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Description("test with priority 2")
    @Test(priority = 2)
    public void testName5() throws Exception {
        System.out.println("Test name: " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Description("test with priority 1")
    @Test(priority = 1)
    public void testName6() throws Exception {
        System.out.println("Test name: " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Description("test with priority 0, highest priority")
    @Test(priority = 0, description = "High priority")
    public void testName7() throws Exception {
        System.out.println("Test name: " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

}
