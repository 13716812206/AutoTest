package com.test.testcase;

import com.test.config.TestConfig;
import com.test.model.AddUserCase;
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

public class AddUserTest {


    @Test(dependsOnGroups = "loginTrue", description = "添加用户测试")
    public void addUser() throws IOException, InterruptedException {

        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        AddUserCase addUserCase = sqlSession.selectOne("addUserCase", 1);
        System.out.println(addUserCase.toString());
        System.out.println(TestConfig.addUserUrl);


        String result = getResult(addUserCase);
        Thread.sleep(3000);
        User user=sqlSession.selectOne("addUser",addUserCase);
//        System.out.println(user.toString());
        Assert.assertEquals(addUserCase.getExpected(),result);


    }

    private String getResult(AddUserCase addUserCase) throws IOException {
        HttpPost httpPost=new HttpPost(TestConfig.addUserUrl);
        JSONObject param=new JSONObject();
        param.put("userName",addUserCase.getUserName());
        param.put("passWord",addUserCase.getPassWord());
        param.put("sex",addUserCase.getSex());
        param.put("age",addUserCase.getAge());
        param.put("permission",addUserCase.getPermission());
        param.put("isDelete",addUserCase.getIsDelete());

        //设置头信息
        httpPost.addHeader("Content-type","application/json");
        httpPost.addHeader("Cookie",TestConfig.cookieStore.toString());
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        httpPost.setEntity(entity);


        String result;
        HttpResponse response=TestConfig.httpClient.execute(httpPost);
        result= EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result+"===============================");




        return result;
    }
}
