package com.test.httpclient;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestGetCookies {
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
	public void getWithCookies() throws ClientProtocolException, IOException {
		String uri=bundle.getString("getWithCookies_uri");
		HttpGet get = new HttpGet(url+uri);
		DefaultHttpClient client = new DefaultHttpClient();
		//设置cookies信息
		client.setCookieStore(cs);
		HttpResponse response = client.execute(get);
		//获取响应的状态码
		int codes = response.getStatusLine().getStatusCode();
		if (codes==200) {
			String result = EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println(result);
		}
		
	}

}
