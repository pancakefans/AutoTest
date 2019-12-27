package com.test.testng;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class Demo1 {
	@Test
	public void test1() {
		Assert.assertEquals("aaa", "aaa");
	}
	@Test
	public void test2() {
		Assert.assertEquals(1, 2);
	}
	
	@Test
	public void test3() {
		Reporter.log("这是我们自己写的日志");
        throw new RuntimeException("这是我们自己的运行时异常");
	}

}
