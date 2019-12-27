package com.test.testng;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupsOnClass1 {
	public void test1() {
		System.out.println("GroupsOnClass1中test1方法");
	}
	public void test2() {
		System.out.println("GroupsOnClass1中test2方法");
	}
}
