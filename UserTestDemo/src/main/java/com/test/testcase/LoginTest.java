package com.test.testcase;

import com.test.config.TestConfig;
import com.test.model.InterfaceName;
import com.test.model.LoginCase;
import com.test.utils.ConfigFile;
import com.test.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {

    @BeforeTest(groups = "loginTrue", description = "测试准备工作")
    public void beforTest() {
        TestConfig.getUserInfoUrl = ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.getUserListUrl = ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSER);
        TestConfig.updateUserInfoUrl = ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
        TestConfig.httpClient=HttpClients.custom().setDefaultCookieStore(TestConfig.cookieStore).build();
        TestConfig.cookieStore=new BasicCookieStore();
    }

    @Test(groups = "loginTrue", description = "用户登录成功接口测试")
    public void loginTrue() throws IOException {
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        LoginCase loginCase = sqlSession.selectOne("loginCase", 1);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);


        //发送请求
        String result=getResult(loginCase);

        //验证结果

        Assert.assertEquals(loginCase.getExpected(),result);
    }

    private String getResult(LoginCase loginCase) throws IOException {

        HttpPost httpPost=new HttpPost(TestConfig.loginUrl);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("userName",loginCase.getUserName());
        jsonObject.put("passWord",loginCase.getPassWord());

        httpPost.addHeader("content-type","application/json");
        StringEntity entity=new StringEntity(jsonObject.toString(),"utf-8");
        httpPost.setEntity(entity);
        String result;
        HttpResponse response=TestConfig.httpClient.execute(httpPost);
        result=  EntityUtils.toString(response.getEntity());

        return result;
    }

    @Test(groups = "loginFalse", description = "用户登录失败接口测试")
    public void loginFalse() throws IOException {
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        LoginCase loginCase = sqlSession.selectOne("loginCase", 2);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);
        String result=getResult(loginCase);
        Assert.assertEquals(loginCase.getExpected(),result);
    }
}
