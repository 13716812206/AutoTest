package com.test.testng.suite;

import org.testng.annotations.*;

public class SuiteConfig {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("before suite运行了");

    }

    @AfterSuite
    public void afterSuite() {

        System.out.println("after suite运行了");
    }


    @BeforeTest
    public void beforeTest() {
        System.out.println("beforeTest运行了");

    }

    @AfterTest
    public void afterTest() {
        System.out.println("afterTest运行了");

    }
}
