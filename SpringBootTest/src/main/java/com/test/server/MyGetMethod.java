package com.test.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
public class MyGetMethod {

    @RequestMapping(value = "/getCookies", method = RequestMethod.GET)
    public String getCookies(HttpServletResponse response) {
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);

        return "恭喜你获得Cookies成功";
    }
    /**
     * 要求客户端携带cookies访问
     *
     */
    @RequestMapping(value = ("/get/with/cookies"), method = RequestMethod.GET)

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


}
