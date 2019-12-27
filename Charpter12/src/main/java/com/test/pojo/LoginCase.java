package com.test.pojo;

import lombok.Data;

@Data
public class LoginCase {
    private int id;
    private String username;
    private String password;
    private String expected;

}
