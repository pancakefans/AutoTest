package com.test.testng;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class IgnoreTest {
	@Test
	public void ignore1() {
		System.out.println("ignore1");
	}
	@Test(enabled = false)
	public void ignore2() {
		System.out.println("ignore2");
	}
	@BeforeTest
	public void beforeTest1() {
		System.out.println("IgnoreTest-beforeTest-在测试方法开始之前运行");
	}
	@AfterTest
	public void afterTest1() {
		System.out.println("IgnoreTest-afterTest1-在测试方法结束之后运行");
	}

}
