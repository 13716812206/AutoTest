package com.test.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyHttpClientDemo {


    @Test
    public void test1() throws IOException {


        //用来存放我们的结果
        String result;
        //创建htpp get请求
        HttpGet get = new HttpGet("http://www.baidu.com");
        //创建HttpClient对象
        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(get);
        HttpEntity httpEntity = response.getEntity();
        result= EntityUtils.toString(httpEntity,"UTF-8");
        System.out.println(result);

    }


}
