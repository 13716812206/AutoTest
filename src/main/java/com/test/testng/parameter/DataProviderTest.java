package com.test.testng.parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataProviderTest {



    @Test(dataProvider = "student")
    public void parameterTest(String name, int age) {

        System.out.println("name=====" + name + ",,,,age=====" + age);

    }
    @DataProvider(name = "student")
    public Object[][] providerData(){

        Object[][] object=new Object[][]{
                {"张三",10},{"李四",20},{"王五",30},{"赵六",40}

        };
        return object;
    }

    @Test(dataProvider = "data")
    public void test1(String name, int age) {
        System.out.println("测试方法111111111111name=====" + name + ",,,,age=====" + age);
    }

    @Test(dataProvider = "data")
    public void test2(String name, int age) {
        System.out.println("测试方法22222222222222name=====" + name + ",,,,age=====" + age);
    }


    @DataProvider(name = "data")
    public Object[][] providerDatas(Method method) {
        Object[][] object = null;


        if (method.getName().equals("test1")) {
            object = new Object[][]{
                    {"张三", 10}, {"李四", 20}, {"王五", 30}, {"赵六", 40}

            };

        } else if (method.getName().equals("test2")) {
            object = new Object[][]{
                    {"张", 10}, {"李", 20}, {"王", 30}, {"赵", 40}

            };
        }

        return object;
    }


}
