package com.test.httpclient.cookie;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {

    private String url;
    private ResourceBundle bundle;
    private BasicCookieStore cookieStore;

    @BeforeTest
    public void beforeTest() {
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }

    @Test()
    public void testGetCookies() throws IOException {

        String result;
        String cookieUrl = bundle.getString("test.getdemo");
        String testUrl = url + cookieUrl;
        CloseableHttpClient client=HttpClients.createDefault();
        HttpGet httpGet =new HttpGet(testUrl);
        RequestConfig requestConfig=RequestConfig.custom().setConnectionRequestTimeout(1000).setConnectTimeout(10000).build();
//        HttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(httpGet);
        result = EntityUtils.toString(response.getEntity());
        System.out.println(result);


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
            System.out.println(result);
            List<Cookie> list = this.cookieStore.getCookies();
            for (Cookie cook : list) {

                String name = cook.getName();
                String value = cook.getValue();
                System.out.println("Cookies========" + name + ":" + value);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test(dependsOnMethods = {"testGetCookiesNew"})
    public void testGetWithCookies() throws IOException, URISyntaxException {

        String uri = bundle.getString("test.getWithCookies");
        String testUrl = this.url + uri;
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        HttpGet httpGet = new HttpGet(testUrl);
        httpGet.addHeader("Cookie",this.cookieStore.toString());
        System.out.println(this.cookieStore.toString());

        CloseableHttpResponse response = client.execute(httpGet);

       int code= response.getStatusLine().getStatusCode();
        if(code==200){
            String result = EntityUtils.toString(response.getEntity());
            System.out.println("执行成功============"+result);
        }else {
            System.out.println("失败");
        }


        List<NameValuePair> params=new ArrayList<>();
        params.add(new BasicNameValuePair("",""));

        URI uris = new URIBuilder().setScheme("http")
                .setHost("")
                .setPath("")
                .setPort(8888)
                .setParameters(params)
                .build();





    }


}
