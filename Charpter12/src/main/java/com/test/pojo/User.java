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

    @Override
    public String toString(){
        return(
           "{id:"+id+","+
           "userName:"+username+","+
           "password:"+password+","+
           "age:"+age+","+
           "sex:"+sex+","+
           "permission:"+permisson+","+
           "isDelete:"+isDelete+"}"
                );
    }

}
