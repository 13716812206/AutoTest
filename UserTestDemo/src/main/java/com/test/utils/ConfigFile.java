package com.test.utils;

import com.test.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigFile {

    private static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);

    public static String getUrl(InterfaceName name) {
        String address = bundle.getString("test.url");
        String uri = "";
        String testurl;
        if (name == InterfaceName.GETUSERINFO) {
            uri = bundle.getString("getUserInfo.url");
        } else if (name == InterfaceName.ADDUSER) {

            uri = bundle.getString("addUser.url");
        } else if (name == InterfaceName.GETUSERLIST) {

            uri = bundle.getString("getUserList.url");
        } else if (name == InterfaceName.UPDATEUSERINFO) {
            uri = bundle.getString("updateUserInfo.url");

        } else if (name == InterfaceName.LOGIN) {

            uri = bundle.getString("login.url");
        }

        testurl = address + uri;
        return testurl;
    }


}
