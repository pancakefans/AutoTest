package com.test.pojo;

import lombok.Data;

@Data
public class User {
	private int id;
    private String username;
    private String password;
    private int age;
    private int sex;
    private int permisson;
    private int isDelete;

}
