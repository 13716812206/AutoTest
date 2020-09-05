package com.test.model;

import lombok.Data;

@Data
public class User {

    private int id;
    private String userName;
    private String passWord;
    private int age;
    private int sex;
    private int permission;
    private int isDelete;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", permission=" + permission +
                ", isDelete=" + isDelete +
                '}';
    }
}
