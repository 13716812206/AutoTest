package com.test.testcase;

import com.test.config.TestConfig;
import com.test.model.InterfaceName;
import com.test.model.LoginCase;
import com.test.utils.ConfigFile;
import com.test.utils.DatabaseUtil;
import org.apache.http.impl.client.HttpClients;
import org.apache.ibatis.session.SqlSession;
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
        TestConfig.httpClient = HttpClients.createDefault();

    }

    @Test(groups = "loginTrue", description = "用户登录成功接口测试")
    public void loginTrue() throws IOException {
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        LoginCase loginCase = sqlSession.selectOne("loginCase", 1);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);
    }

    @Test(groups = "loginFalse", description = "用户登录失败接口测试")
    public void loginFalse() throws IOException {
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        LoginCase loginCase = sqlSession.selectOne("loginCase", 2);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);
    }
}
