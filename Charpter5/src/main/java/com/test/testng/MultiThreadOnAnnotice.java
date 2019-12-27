package com.test.testng;

import org.testng.annotations.Test;

public class MultiThreadOnAnnotice {
	/*
	 * nvocationCount----表示执行的次数
		threadPoolSize-----表示线程池的内线程的个数
	 * */
	@Test(invocationCount = 10,threadPoolSize = 3)
	public void test1() {
		System.out.println("MultiThreadOnAnnotice_test1_Thread Id :"+Thread.currentThread().getId());
	}
}
