package com.test.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/", description = "get方法")
public class MyGetMethod {

    @RequestMapping(value = "/getCookies", method = RequestMethod.GET)
    @ApiOperation(value = "获取cookie的get方法", httpMethod = "GET")
    public String getCookies(HttpServletResponse response) {
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);

        return "恭喜你获得Cookies成功";
    }

    /**
     * 要求客户端携带cookies访问
     */
    @RequestMapping(value = ("/get/with/cookies"), method = RequestMethod.GET)
    @ApiOperation(value = "携带cookie的get方法", httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            return "必须携带cookie来";
        }
        for (Cookie cookie : cookies
        ) {
            if (cookie.getName().equals("login") && cookie.getValue().equals("true")) {
                return "恭喜你,登录成功";
            }
        }
        return "你必须携带cookies来";


    }


    /**
     * 开发一个需要携带参数才能访问的geu请求
     */
    @ApiOperation(value = "携带参数的get方法1", httpMethod = "GET")
    @RequestMapping(value = "/getwithParameter", method = RequestMethod.GET)
    public Map<String, Integer> getList(@RequestParam Integer start, @RequestParam Integer end) {


        Map<String, Integer> list = new HashMap<>();
        list.put("鞋", 300);
        list.put("零食", 111);
        list.put("衣服", 1111);
        return list;
    }


    /**
     * 第二种携带参数访问的get请求
     */
    @ApiOperation(value = "携带参数的get方法2", httpMethod = "GET")
    @RequestMapping(value = "/getWithParameter/{number}/{numbers}",method = RequestMethod.GET)
    public Map<String, Integer> getListSecond(@PathVariable String number, @PathVariable String numbers) {


        Map<String, Integer> list = new HashMap<>();
        list.put("鞋2", 300);
        list.put("零食2", 111);
        list.put("衣服2", 1111);
        return list;
    }

}
