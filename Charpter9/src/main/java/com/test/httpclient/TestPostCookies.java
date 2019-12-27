package com.test.httpclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestPostCookies {
	private ResourceBundle bundle;
	private CookieStore cs;
	private String url;
	
	@BeforeTest
	public void beforeTest() {
		bundle=ResourceBundle.getBundle("application");
		url=bundle.getString("test_url");
	}
	@Test
	public void getCookies() throws ClientProtocolException, IOException {
		String uri=bundle.getString("getCookies_uri");
		HttpGet get = new HttpGet(url+uri);
		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(get);
		String result=EntityUtils.toString(response.getEntity(),"utf-8");
		System.out.println(result);
		//获取cookies信息
		cs = client.getCookieStore();
		List<Cookie> cookies = cs.getCookies();
		for(Cookie c:cookies) {
			String name=c.getName();
			String value=c.getValue();
			System.out.println(name+":"+value);
		}
	}
	@Test(dependsOnMethods = {"getCookies"})
	public void postWithCookies() throws ClientProtocolException, IOException {
		String uri=bundle.getString("postWithCookies_uri");
		HttpPost post = new HttpPost(url+uri);
		DefaultHttpClient client = new DefaultHttpClient();
		//添加post请求参数，用json传值,pom文件中引入json依赖
		JSONObject json = new JSONObject();
		json.put("name", "huhansan");
		json.put("age", "18");
		//设置请求头信息
		post.setHeader("content-type", "application/json");
		//将参数信息添加到方法中
		StringEntity entity = new StringEntity(json.toString(), "utf-8");
		post.setEntity(entity);
		//设置cookies信息
		client.setCookieStore(cs);
		//发送请求
		HttpResponse response=client.execute(post);
		//接收响应信息
		String result=EntityUtils.toString(response.getEntity(),"utf-8");
		//将返回的响应结果字符串转化成为json对象
		JSONObject resultJson = new JSONObject(result);
		//获取响应结果值
		String success=resultJson.getString("huhansan");
		String status=resultJson.getString("status");
		Assert.assertEquals("success", success);
		Assert.assertEquals("1", status);
		
	}
}
