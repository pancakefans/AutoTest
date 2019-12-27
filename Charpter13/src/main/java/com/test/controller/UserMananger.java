package com.test.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.pojo.User;
import com.test.utils.CookiesUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
@Log4j
@RestController
@Api(value = "/v1",description = "用户管理系统")
@RequestMapping("/v1")
public class UserMananger {
	@Autowired
	private SqlSessionTemplate template;
	
	@ApiOperation(value = "登录接口")
	@PostMapping("/login")
	public Boolean login(HttpServletResponse response,@RequestBody User user) {
		int i=template.selectOne("login",user);
		if (i==1) {
			Cookie cookie= new Cookie("login", "true");
			response.addCookie(cookie);
			log.info("登录的用户是:"+user.getUsername());
			return true;
		}
		return false;
	}
	
	@ApiOperation(value = "添加用户接口")
	@PostMapping("/addUser")
	public Boolean addUser(HttpServletRequest request,@RequestBody User user) {
		Boolean b=CookiesUtil.verifyCookies(request);
		int i=0;
		if (b) {
			i= template.insert("addUser",user);
		}
		if (i>0) {
			log.info("添加用户的数量是:"+i);
			return true;
		}
		return false;
	}
	
	@ApiOperation(value = "获取用户（列表）信息接口")
	@PostMapping("/getUserInfo")
	public List<User> getUserInfo(HttpServletRequest request,@RequestBody User user){
		Boolean b =CookiesUtil.verifyCookies(request);
		if (b) {
			List<User> users =template.selectList("getUserInfo",user);
			log.info("getUserInfo获取到的用户数量是"+users.size());
            return users;
		}
		return null;
	}
	
	@ApiOperation(value = "更新删除用户接口")
	@PostMapping("/updateUserInfo")
	public int updateUser(HttpServletRequest request,@RequestBody User user) {
		boolean b = CookiesUtil.verifyCookies(request);
		int i=0;
		if (b) {
			i=template.update("updateUser",user);
			
		}
		log.info("更新数据的条目数为:"+i);
		return i;
	}

}
