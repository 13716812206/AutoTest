package com.test.testcase;

import com.test.config.TestConfig;
import com.test.model.UpdateUserInfoCase;
import com.test.model.User;
import com.test.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class UpdateUserInfoTest {

    @Test(dependsOnGroups = "loginTrue", description = "修改用户信息")
    public void updateUserInfo() throws IOException, InterruptedException {
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        UpdateUserInfoCase updateUserInfoCase = sqlSession.selectOne("updateUserInfoCase", 1);
        System.out.println(updateUserInfoCase.toString());
        System.out.println(TestConfig.updateUserInfoUrl);


        int result = getResult(updateUserInfoCase);
        Thread.sleep(3000);
        User user = sqlSession.selectOne(updateUserInfoCase.getExpected(), updateUserInfoCase);
//        System.out.println(user.toString());
        Thread.sleep(2000);
//        Assert.assertNotNull(user);
//        Assert.assertNotNull(result);


    }

    private int getResult(UpdateUserInfoCase updateUserInfoCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.updateUserInfoUrl);
        JSONObject param = new JSONObject();
        param.put("id", updateUserInfoCase.getUserId());
        param.put("userName", updateUserInfoCase.getUserName());
        param.put("sex", updateUserInfoCase.getSex());
        param.put("age", updateUserInfoCase.getAge());
        param.put("permission", updateUserInfoCase.getPermission());
        param.put("isDelete", updateUserInfoCase.getIsDelete());

        post.setHeader("content-type", "application/json");
        post.setHeader("Cookie", TestConfig.cookieStore.toString());


        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);

        HttpResponse response = TestConfig.httpClient.execute(post);

        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        return Integer.parseInt(result);


    }

    @Test(dependsOnGroups = "loginTrue", description = "删除用户信息")
    public void deleteUserInfo() throws IOException, InterruptedException {
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        UpdateUserInfoCase updateUserInfoCase = sqlSession.selectOne("updateUserInfoCase", 2);
        System.out.println(updateUserInfoCase.toString());
        System.out.println(TestConfig.updateUserInfoUrl);


        int result = getResult(updateUserInfoCase);
        Thread.sleep(4000);

        User user = sqlSession.selectOne(updateUserInfoCase.getExpected(), updateUserInfoCase);
        Thread.sleep(2000);
//        Assert.assertNotNull(user);
//        Assert.assertNotNull(result);

    }

}
