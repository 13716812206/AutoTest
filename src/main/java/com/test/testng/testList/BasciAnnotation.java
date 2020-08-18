package com.test.testng.testList;

import org.testng.annotations.*;

public class BasciAnnotation {

    @Test(priority = 1)
    public void testCase1() {
        System.out.println("Test这是测试用例1");

    }

    @Test
    public void testCase2() {
        System.out.println("Test这是测试用例2");

    }

    @BeforeMethod
    public void beforeTest() {

        System.out.println("BeforeMethod这是在测试方法之前运行的");


    }

    @AfterMethod
    public void afterTest() {

        System.out.println("AfterMethod这是在测试方法之后运行的");


    }

    @BeforeClass
    public void beforeClas() {

        System.out.println("BeforeClass这是在类运行之前执行的");


    }

    @AfterClass
    public void afterClass() {

        System.out.println("AfterClass这是在类运行之前执行的");


    }

    @BeforeSuite
    public void beforeSuite() {

        System.out.println("beforeSuite测试套件");

    }
    @AfterSuite
    public void afterSuite() {

        System.out.println("afterSuite测试套件");

    }
}
