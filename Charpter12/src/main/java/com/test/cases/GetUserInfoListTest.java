package com.test.cases;

import java.io.IOException;
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
import com.test.pojo.GetUserListCase;
import com.test.pojo.User;
import com.test.utils.DatabaseUtil;

public class GetUserInfoListTest {
	@Test(dependsOnGroups = "loginTrue",description = "获取sex为0的用户信息")
	public void getUserInfoList() throws IOException {
		SqlSession session = DatabaseUtil.getSqlSession();
        GetUserListCase getUserListCase = session.selectOne("getUserInfoListCase",1);
        JSONArray resultJson=getJsonResult(getUserListCase);
        System.out.println(resultJson.toString());
        //验证
        List<User> users =session.selectList(getUserListCase.getExpected(), getUserListCase);
        JSONArray array = new JSONArray(users);
        System.out.println(array.toString());
        Assert.assertEquals(array.length(), resultJson.length());
        for (int i = 0; i < resultJson.length(); i++) {
			JSONObject actual=(JSONObject) array.get(i);
			JSONObject except=(JSONObject) resultJson.get(i);
			Assert.assertEquals(actual.toString(), except.toString());
		}
	}

	private JSONArray getJsonResult(GetUserListCase getUserListCase) throws ClientProtocolException, IOException {
		HttpPost post = new HttpPost(TestConfig.getUserListUrl);
        JSONObject param = new JSONObject();
        param.put("sex",getUserListCase.getSex());
        post.setHeader("content-type","application/json");
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //设置cookies
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        String result;
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        JSONArray array = new JSONArray(result);
		return array;
	}
}
