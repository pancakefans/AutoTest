package com.test.cases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.config.TestConfig;
import com.test.pojo.UpdateUserInfoCase;
import com.test.pojo.User;
import com.test.utils.DatabaseUtil;

public class UpdateUserInfoTest {
	@Test(dependsOnGroups = "loginTrue",description = "更改用户信息")
	public void updateUser() throws IOException, InterruptedException {
		SqlSession session = DatabaseUtil.getSqlSession();
		UpdateUserInfoCase updateUserInfoCase =session.selectOne("updateUserInfoCase", 1);
		int result =getResult(updateUserInfoCase);
		User user =	session.selectOne(updateUserInfoCase.getExpected(), updateUserInfoCase);
		Assert.assertNotNull(user);
	    Assert.assertNotNull(result);
	}
	@Test(dependsOnGroups = "loginTrue",description = "删除用户信息")
	public void deleteUser() throws IOException, InterruptedException {
		SqlSession session = DatabaseUtil.getSqlSession();
		UpdateUserInfoCase updateUserInfoCase =session.selectOne("updateUserInfoCase", 2);
		int result =getResult(updateUserInfoCase);
		User user =	session.selectOne(updateUserInfoCase.getExpected(), updateUserInfoCase);
		Assert.assertNotNull(user);
	    Assert.assertNotNull(result);
	}

	private int getResult(UpdateUserInfoCase updateUserInfoCase) throws ClientProtocolException, IOException, InterruptedException {
		HttpPost post = new HttpPost(TestConfig.updateUserInfoUrl);
        JSONObject param = new JSONObject();
        param.put("username",updateUserInfoCase.getUsername());
        param.put("sex",updateUserInfoCase.getSex());
        param.put("age",updateUserInfoCase.getAge());
        param.put("permisson",updateUserInfoCase.getPermisson());
        param.put("isDelete",updateUserInfoCase.getIsDelete());
        param.put("id",updateUserInfoCase.getUserid());
        post.setHeader("content-type","application/json");
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //设置cookies
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        String result;
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        Thread.sleep(10000);
        return  Integer.parseInt(result);
	}

}
