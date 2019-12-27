package com.test.testng;

import org.testng.annotations.Test;

public class DependsTest {
	@Test
	public void test1() {
		System.out.println("test1 run");
		throw new RuntimeException();
	}
	@Test(dependsOnMethods = {"test1"})
	public void test2() {
		System.out.println("test2 run");
	}
}
