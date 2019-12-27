package com.test.pojo;


import lombok.Data;

@Data
public class UpdateUserInfoCase {
    private int id;
    private int userid;
    private String username;
    private int sex;
    private int age;
    private int permisson;
    private int isDelete;
    private String expected;
}
