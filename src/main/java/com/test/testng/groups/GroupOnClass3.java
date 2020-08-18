package com.test.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "teacher")
public class GroupOnClass3 {
    public void teacher1(){
        System.out.println("GroupOnClass2中的teacher1执行");
    }
    public void teacher2(){
        System.out.println("GroupOnClass2中的teacher2执行");
    }
}
