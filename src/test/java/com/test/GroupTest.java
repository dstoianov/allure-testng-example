package com.test;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

/**
 * Created by Funker on 27.05.2015.
 */
public class GroupTest {

    @BeforeGroups("database")
    public void setupDB() {
        System.out.println("setupDB()");
    }

    @AfterGroups("database")
    public void cleanDB() {
        System.out.println("cleanDB()");
    }

    @Test(groups = "selenium-test")
    public void runSelenium() {
        System.out.println("runSelenium()");
    }

    @Test(groups = "selenium-test")
    public void runSelenium1() {
        System.out.println("runSelenium()1");
    }

    @Test(groups = "database")
    public void testConnectOracle() {
        System.out.println("testConnectOracle()");
    }

    @Test(groups = "database")
    public void testConnectMsSQL() {
        System.out.println("testConnectMsSQL");
    }

    @Test(dependsOnGroups = {"database", "selenium-test"})
    public void runFinal() {
        System.out.println("runFinal");
    }
}
