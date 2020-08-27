package com.test.httpclient.cookie;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {
    private String url;
    private ResourceBundle bundle;
    private BasicCookieStore cookieStore;

    @BeforeTest
    public void beforeTest() {
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }

    @Test
    public void testGetCookiesNew() {
        String result;
        String cookieUrl = bundle.getString("getCookies.url");
        String testUrl = url + cookieUrl;

        try {
            HttpGet httpGet = new HttpGet(testUrl);
            this.cookieStore = new BasicCookieStore();

            CloseableHttpClient closeableHttpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
            CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
            result = EntityUtils.toString(response.getEntity());
            List<Cookie> list = cookieStore.getCookies();
            for (Cookie cook : list) {

                String name = cook.getName();
                String value = cook.getValue();
                System.out.println("Cookies========" + name + ":" + value + "====");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test(dependsOnMethods = {"testGetCookiesNew"})
    public void testPostMethod() throws IOException {

        String posturl = url + bundle.getString("test.postWithCookies");
        System.out.println(posturl);

        CloseableHttpClient closeableHttpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        HttpPost httpPost = new HttpPost(posturl);
        //添加参数
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "huhansan");
        jsonObject.put("age", "18");
        httpPost.setHeader("content-type", "application/json");
        httpPost.addHeader("Cookie", cookieStore.toString());
        //将参数信息添加到请求中
        StringEntity entity = new StringEntity(jsonObject.toString(), "UTF-8");
        httpPost.setEntity(entity);


        System.out.println(cookieStore.toString());
        CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
        String result = EntityUtils.toString(response.getEntity());
        System.out.println("================="+result);
        JSONObject json = new JSONObject(result);

        Assert.assertEquals("success", json.get("name"));

    }

}
