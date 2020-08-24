package com.extent.reports;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class ExtentReportsDemo {


    @Test
    public void test1() {
        Assert.assertEquals(1, 2);

    }

    @Test
    public void test2() {
        Assert.assertEquals(1, 1);

    }
    @Test
    public void test3() {
        Assert.assertEquals("111", "111");

    }

    @Test
    public void logTest() {
        Reporter.log("自己打印的日志");
        throw new RuntimeException("自己抛出的一个异常");
    }
}
