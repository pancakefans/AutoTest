package com.test.pojo;

import lombok.Data;

@Data
public class AddUserCase {
    private String username;
    private String password;
    private int sex;
    private int age;
    private int permisson;
    private int isDelete;
    private String expected;
}
