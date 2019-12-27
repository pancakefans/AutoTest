package com.test.testng;

import org.testng.annotations.Test;

public class MutliThreadOnXml {
	
	@Test
	public void test1() {
		System.out.println("MutliThreadOnXml_test1_Thread Id :"+Thread.currentThread().getId());
	}
	@Test
	public void test2() {
		System.out.println("MutliThreadOnXml_test2_Thread Id :"+Thread.currentThread().getId());
	}
	@Test
	public void test3() {
		System.out.println("MutliThreadOnXml_test3_Thread Id :"+Thread.currentThread().getId());
	}

}
