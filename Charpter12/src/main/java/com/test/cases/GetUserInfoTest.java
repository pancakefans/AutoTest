package com.test.cases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.test.pojo.GetUserInfoCase;
import com.test.pojo.User;
import com.test.utils.DatabaseUtil;

public class GetUserInfoTest {
	@Test(dependsOnGroups = "loginTrue",description = "获取userid为1的用户信息")
	public void getUserInfo() throws IOException {
		SqlSession session = DatabaseUtil.getSqlSession();
        GetUserInfoCase getUserInfoCase = session.selectOne("getUserInfoCase",1);
        JSONArray resultJson = getJsonResult(getUserInfoCase);
        User user=session.selectOne(getUserInfoCase.getExpected(), getUserInfoCase);
        System.out.println(user);
        List<User> users= new ArrayList<User>();
        users.add(user);
        JSONArray jsonArray = new JSONArray(users);
        JSONArray jsonArray1 = new JSONArray(resultJson.getString(0));
        Assert.assertEquals(jsonArray.toString(),jsonArray1.toString());
	}

	private JSONArray getJsonResult(GetUserInfoCase getUserInfoCase) throws ClientProtocolException, IOException {
		HttpPost post = new HttpPost(TestConfig.getUserInfoUrl);
        JSONObject param = new JSONObject();
        param.put("id",getUserInfoCase.getUserid());
        post.setHeader("content-type","application/json");
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //设置cookies
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        String result;
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        List resultList = Arrays.asList(result);
        JSONArray array = new JSONArray(resultList);
		return array;
	}
}
