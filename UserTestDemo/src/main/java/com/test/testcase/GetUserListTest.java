package com.test.testcase;

import com.test.config.TestConfig;
import com.test.model.GetUserListCase;
import com.test.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetUserListTest {


    @Test(dependsOnGroups = "loginTrue",description = "获取用户列表测试")
    public void getUserList() throws IOException {
        SqlSession sqlSession= DatabaseUtil.getSqlSession();
        GetUserListCase getUserListCase=sqlSession.selectOne("getUserListCase",1);
        System.out.println(getUserListCase.toString());
        System.out.println(TestConfig.getUserListUrl);


    }
}
