package com.test.cases;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.config.TestConfig;
import com.test.pojo.AddUserCase;
import com.test.pojo.User;
import com.test.utils.DatabaseUtil;

public class AddUserTest {
	
	@Test(dependsOnGroups = "loginTrue",description = "添加用户接口测试")
	public void addUser() throws IOException, InterruptedException {
		SqlSession session = DatabaseUtil.getSqlSession();
        AddUserCase addUserCase = session.selectOne("addUserCase",2);
        //发送请求
        String result = getResult(addUserCase);
//		Thread.sleep(5000); 
//        User user = session.selectOne("addUser",addUserCase);
//        System.out.println(user.toString());
        //验证结果
        Assert.assertEquals(addUserCase.getExpected(),result);
	}

	private String getResult(AddUserCase addUserCase) throws ClientProtocolException, IOException {
		HttpPost post = new HttpPost(TestConfig.addUserUrl);
        JSONObject param = new JSONObject();
        param.put("username",addUserCase.getUsername());
        param.put("password",addUserCase.getPassword());
        param.put("age",addUserCase.getAge());
        param.put("sex",addUserCase.getSex());
        param.put("permisson",addUserCase.getPermisson());
        param.put("isDelete",addUserCase.getIsDelete());
        post.setHeader("content-type","application/json");
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //设置cookies
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        String result;
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        return  result;
	}
}
