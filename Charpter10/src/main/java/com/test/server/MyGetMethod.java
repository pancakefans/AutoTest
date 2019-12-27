package com.test.server;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@RestController加在类上面的注解，使得类里面的每个方法都将json/xml返回数据加返回到前台页面中
@RestController
@Api(value = "/",description = "这是我全部的get方法")
public class MyGetMethod {
	//@GetMapping是一个组合注解，是@RequestMapping(method = RequestMethod.GET)的缩写
	@GetMapping("/getCookies")
	@ApiOperation(value = "通过这个方法可以获取到Cookies")
	public String getCookies(HttpServletResponse response) {
		 Cookie cookie = new Cookie("login","true");
	     response.addCookie(cookie);
	     return "恭喜你获取cookies成功";
	}
	//要求客户端携带cookies才能访问
	@GetMapping("/getWithCookies")
	@ApiOperation(value = "要求客户端携带cookies访问")
	public String getWithCookies(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return "必须携带cookie访问";
		}
		for (Cookie c:cookies) {
			if (c.getName().equals("login")&&c.getValue().equals("true")) {
				return "恭喜你登录成功";
			}
		}
		return "必须携带cookie访问";
	}
	 /**
     * 开发一个需要携带参数才能访问的get请求。
     * 第一种实现方式 url: key=value&key=value
     * 我们来模拟获取商品列表
     * 解决参数不一致可以用@RequestParam("start")Integer s
     */
	@GetMapping("/getWithParam")
	@ApiOperation(value = "需求携带参数才能访问的get请求方法一")
	public Map<String, Double> getWithParam(Integer start,Integer end){
		Map<String , Double> myMap = new HashMap<String, Double>();
		myMap.put("苹果", 5.5);
		myMap.put("香蕉", 2.8);
		return myMap;
	}
	/**
     * 第二种需要携带参数访问的get请求
     * url:ip:port/get/with/param/10/20
     */
	@GetMapping("/getWithParam/{start}/{end}")
	@ApiOperation(value = "需求携带参数才能访问的get请求方法二")
	public Map<String, Double> getWithParam2(@PathVariable Integer start,@PathVariable Integer end){
		Map<String , Double> myMap = new HashMap<String, Double>();
		myMap.put("苹果", 5.5);
		myMap.put("香蕉", 2.8);
		return myMap;
	}
}
