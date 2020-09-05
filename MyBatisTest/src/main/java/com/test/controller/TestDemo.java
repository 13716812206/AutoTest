package com.test.controller;

import com.test.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j
@RestController
@Api(value = "v1", description = "这是我第一个版本的demo")
@RequestMapping("v1")
public class TestDemo {


    //获取执行sql的对象
    @Autowired
    private SqlSessionTemplate template;

    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户数", httpMethod = "GET")
    public int getUserList() {

        return template.selectOne("getUserList");

    }


    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ApiOperation(value = "添加用户", httpMethod = "POST")
    public int adduser(@RequestBody User user) {
        int result = template.insert("addUser", user);
        return result;
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ApiOperation(value = "修改用户", httpMethod = "POST")
    public int updateUser(@RequestBody User user) {
        int result = template.update("updateUser", user);
        return result;
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    @ApiOperation(value = "修改用户", httpMethod = "POST")
    public int deleteUser(@RequestParam int id) {
        int result = template.delete("deleteUser", id);
        return result;
    }


}
