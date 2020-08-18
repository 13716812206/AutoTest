package com.test.testng.testList;

import org.testng.annotations.Test;

public class TimeOutTest {



    @Test(timeOut = 2000)
    public void timeoutSuccess() throws InterruptedException {
        Thread.sleep(1000);

    }
    @Test(timeOut = 1000)
    public void timeoutfail() throws InterruptedException {
        Thread.sleep(2000);

    }







}
