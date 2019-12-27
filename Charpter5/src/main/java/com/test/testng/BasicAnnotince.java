package com.test.testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BasicAnnotince {
//	最基本的注解，用来把方法标记为测试的一部分
	@Test(groups = "1")
	public void testCase1() {
		System.out.println("我是测试用例1");
	}
	@Test(groups = "2")
	public void testCase2() {
		System.out.println("我是测试用例2");
	}
	@BeforeMethod
	public void beforeMethod1() {
		System.out.println("beforeMethod-在每一个测试用例执行之前运行");
	}
	@AfterMethod
	public void afterMethod2() {
		System.out.println("afterMethod-在每一个测试用例执行之后运行");
	}
	@BeforeClass
	public void beforeClass1() {
		System.out.println("beforeClass1-在当前类中方法运行之前调用");
	}
	@AfterClass
	public void afterClass1() {
		System.out.println("afterClass1-在当前类中所有方法运行之后调用");
	}
	@BeforeSuite
	public void beforeSuite1() {
		System.out.println("beforeSuite运行了");
	}
	@AfterSuite
	public void afterSuite1() {
		System.out.println("afterSuite运行了");
	}
	@BeforeTest
	public void beforeTest1() {
		System.out.println("beforeTest-在测试方法开始之前运行");
	}
	@AfterTest
	public void afterTest1() {
		System.out.println("afterTest1-在测试方法结束之后运行");
	}
	@BeforeGroups("1")
	public void beforeGroups() {
		System.out.println("beforeGroups-111111");
	}
	@AfterGroups("1")
	public void afterGroups() {
		System.out.println("afterGroups-111111");
	}
}
