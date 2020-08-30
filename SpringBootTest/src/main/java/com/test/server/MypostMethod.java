package com.test.server;

import com.test.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = ("/"), description = "这是我的post请求")
@RequestMapping("/v1")
public class MypostMethod {


    private static Cookie cookies;

    @RequestMapping(value = "/postLogin", method = RequestMethod.POST)
    @ApiOperation(value = "登录接口，返回cookie", httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "用户名", required = true) String username,
                        @RequestParam(value = "密码", required = false) String password
    ) {
        if (username.equals("zhangsan") && password.equals("123456")) {
            cookies = new Cookie("login", "true");
            response.addCookie(cookies);
            return "用户登录成功";
        }
        return "用户名或者密码错误";

    }

    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表", httpMethod = "POST")
    public String getuserList(HttpServletRequest request,
                              @RequestBody User user) {
        
        User users = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies
        ) {
            if (cookie.getName().equals("login") && cookie.getValue().equals("true") && user.getName().equals("zhangsan")
                    && user.getPassWord().equals("123456")) {
                users = new User();
                user.setName("lisi");
                user.setAge("18");
                user.setSex("man");
                return user.toString();


            }

        }
        return "参数不合法";

    }

}
