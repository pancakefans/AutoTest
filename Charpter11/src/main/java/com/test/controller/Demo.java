package com.test.controller;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.pojo.People;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@Api(value = "v1",description = "这是我的第一个版本的demo")
@RequestMapping("v1")
public class Demo {
	//首先获取一个执行sql语句的对象

    @Autowired
    private SqlSessionTemplate template;

    @GetMapping(value = "/getPeopleCount")
    @ApiOperation(value = "可以获取到用户数")
    public int getUserCount(){
       return template.selectOne("getPeopleCount");
    }
    @PostMapping("/addPeople")
    @ApiOperation(value = "增加用户")
    public int addPeople(@RequestBody People peo) {
    	return template.insert("insPeo",peo);
    }
    
    @PostMapping("/updPeople")
    @ApiOperation(value = "修改用户信息")
    public int updPeople(@RequestBody People peo) {
    	return template.update("updPeo",peo);
    }
    
    
    @PostMapping("/delPeople")
    @ApiOperation(value = "删除用户信息")
    public int delPeople(@RequestBody People peo) {
    	return template.delete("delPeo",peo);
    }
    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public int login(@RequestBody People peo) {
    	return template.selectOne("login",1);
    }
}
