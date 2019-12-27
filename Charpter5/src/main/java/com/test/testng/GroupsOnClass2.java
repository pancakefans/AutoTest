package com.test.testng;

import org.testng.annotations.Test;

@Test(groups = "tea")
public class GroupsOnClass2 {
	public void test1() {
		System.out.println("GroupsOnClass2中test1方法");
	}
	public void test2() {
		System.out.println("GroupsOnClass2中test2方法");
	}
}
