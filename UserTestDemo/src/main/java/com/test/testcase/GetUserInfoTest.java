package com.test.testcase;

import com.google.gson.JsonObject;
import com.test.config.TestConfig;
import com.test.model.GetUserInfoCase;
import com.test.model.User;
import com.test.utils.DatabaseUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetUserInfoTest {


    @Test(dependsOnGroups = "loginTrue", description = "获取用户信息")
    public void getUserInfo() throws IOException {
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        GetUserInfoCase getUserInfoCase = sqlSession.selectOne("getUserInfoCase", 1);
        System.out.println(getUserInfoCase.toString());
        System.out.println(TestConfig.getUserInfoUrl);
        //发送请求获取结果

        JSONArray resultJson = getResult(getUserInfoCase);
        System.out.println("resultJson========"+resultJson.toString());
        User user = sqlSession.selectOne(getUserInfoCase.getExpected(), getUserInfoCase);
        List<User> userList=new ArrayList<>();
        userList.add(user);

        JSONArray jsonArray = new JSONArray(userList);
        JSONArray jsonArray1=new JSONArray(resultJson.getString(0));
        System.out.println(jsonArray.toString());
        Assert.assertEquals(jsonArray.toString(), jsonArray1.toString());


    }

    private JSONArray getResult(GetUserInfoCase getUserInfoCase) throws IOException {
        HttpPost httpPost = new HttpPost(TestConfig.getUserInfoUrl);
        JSONObject object = new JSONObject();
        object.put("id", getUserInfoCase.getUserId());

        httpPost.addHeader("content-type", "application/json");
        httpPost.addHeader("Cookie", TestConfig.cookieStore.toString());


        StringEntity entity = new StringEntity(object.toString(), "utf-8");
        httpPost.setEntity(entity);

        CloseableHttpResponse response = TestConfig.httpClient.execute(httpPost);
        String result = EntityUtils.toString(response.getEntity(), "utf-8");


        JSONArray array = new JSONArray(Arrays.asList(result));
        return array;


    }
}
