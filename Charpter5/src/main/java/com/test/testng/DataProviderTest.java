package com.test.testng;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {
//	dataProvider要跟@DataProvider的name属性值一致
	@Test(dataProvider = "methodData")
	public void Test1(String name,int age) {
		System.out.println("Test111方法中name="+name+",age="+age);
	}
	@Test(dataProvider = "methodData")
	public void Test2(String name,int age) {
		System.out.println("Test222方法中name="+name+",age="+age);
	}
	@DataProvider(name = "methodData")
	public Object[][] dataProvider(Method method){
		Object[][] o = null;
		if (method.getName().equals("Test1")) {
			o= new Object[][] {
				{"张三",23},
			};
		}else if (method.getName().equals("Test2")) {
			o= new Object[][] {
				{"李四",24},
			};
		}else {
			o= new Object[][] {
				{"王五",25}
			};
		}
		
		return o;
	}
}
