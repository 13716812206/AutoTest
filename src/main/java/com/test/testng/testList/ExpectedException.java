package com.test.testng.testList;

import org.testng.annotations.Test;

public class ExpectedException {


    /*
    异常测试
     */
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionFail() {
        System.out.println("这是一个失败的异常测试");
    }
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionSuccess() {
        System.out.println("这是一个成功的异常测试");
        throw new RuntimeException();
    }
}
