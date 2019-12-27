package com.test.server;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.pojo.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "/",description = "这是我全部的post方法")
public class MyPostMethod {
	//这个变量是用来装我们cookies信息的
    private static Cookie cookie;
    @PostMapping("/login")
    @ApiOperation(value = "登录成功，获取cookies信息")
    public String login(@RequestParam(value = "username",required = true)String username,@RequestParam(value = "password",required = true)String password,HttpServletResponse response) {
    	if (username.equals("admin")&&password.equals("admin123")) {
			cookie = new Cookie("login", "true");
    		response.addCookie(cookie);
    		return "登录成功";
		}
    	 return "用户名或者是密码错误！";
    }
    @PostMapping("/getUsersList")
    @ApiOperation(value = "获取用户列表")
    public String getUsersList(HttpServletRequest request,@RequestBody User u){
    	User user;
    	Cookie[] cookies = request.getCookies();
    	if (cookies ==null) {
			return "参数不合法";
		}
    	for(Cookie c:cookies) {
    		if (c.getName().equals("login")&&c.getValue().equals("true")
    				&&u.getUsername().equals("admin")
    				&&u.getPassword().equals("admin123")) {
				user = new User();
				user.setName("李四");
				user.setAge(24);
				user.setUsername("lisi");
				user.setPassword("admin123");
				return user.toString();
			}
    	}
    	return "参数不合法";
    }
}
