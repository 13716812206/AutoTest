package com.test.testcase;

import com.google.gson.JsonObject;
import com.test.config.TestConfig;
import com.test.model.GetUserListCase;
import com.test.model.User;
import com.test.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class GetUserListTest {


    @Test(dependsOnGroups = "loginTrue", description = "获取用户列表测试")
    public void getUserList() throws IOException {
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        GetUserListCase getUserListCase = sqlSession.selectOne("getUserListCase", 1);
        System.out.println(getUserListCase.toString());
        System.out.println(TestConfig.getUserListUrl);


        JSONArray resultJson = getJsonResult(getUserListCase);
        List<User> userList = sqlSession.selectList(getUserListCase.getExpected(), getUserListCase);
        //打印数据
        for (User u : userList) {
            System.out.println("获取的user:" + u.toString());
        }
        JSONArray userListJson=new JSONArray(userList);
        Assert.assertEquals(userListJson.length(),resultJson.length());
        for (int i = 0; i <resultJson.length() ; i++) {
            JsonObject expect= (JsonObject) resultJson.get(i);
            JsonObject actual= (JsonObject) userListJson.get(i);
            Assert.assertEquals(expect.toString(),actual.toString());
        }


    }

    private JSONArray getJsonResult(GetUserListCase getUserListCase) throws IOException {
        HttpPost httpPost=new HttpPost(TestConfig.getUserListUrl);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("userName",getUserListCase.getUserName());
        jsonObject.put("sex",getUserListCase.getSex());
        jsonObject.put("age",getUserListCase.getAge());

        httpPost.setHeader("content-type","application/json");
        StringEntity entity=new StringEntity(jsonObject.toString(),"utf-8");
        httpPost.setEntity(entity);
        httpPost.addHeader("Cookie",TestConfig.cookieStore.toString());
        String result;
        HttpResponse response=TestConfig.httpClient.execute(httpPost);
        result= EntityUtils.toString(response.getEntity(),"utf-8");

        JSONArray jsonArray=new JSONArray(Arrays.asList(result));

        return jsonArray;
    }
}
