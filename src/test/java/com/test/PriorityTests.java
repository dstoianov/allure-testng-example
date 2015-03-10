package com.test;

import org.testng.annotations.Test;

/**
 * Created by Funker on 11.03.2015.
 */
public class PriorityTests {

    @Test(priority = 3)
    public void testName0() throws Exception {
        System.out.println("Test name: " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test(priority = 2)
    public void testName1() throws Exception {
        System.out.println("Test name: " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test(priority = 1)
    public void testName2() throws Exception {
        System.out.println("Test name: " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test(priority = 2)
    public void testName3() throws Exception {
        System.out.println("Test name: " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test(priority = 1)
    public void testName4() throws Exception {
        System.out.println("Test name: " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test(priority = 2)
    public void testName5() throws Exception {
        System.out.println("Test name: " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test(priority = 1)
    public void testName6() throws Exception {
        System.out.println("Test name: " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test(priority = 0, description = "High priority")
    public void testName7() throws Exception {
        System.out.println("Test name: " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

}
