package com.test.testng;

import org.testng.annotations.Test;

public class ExceptionTest {
	@Test(expectedExceptions = RuntimeException.class)
	public void runTimeExceptionSuccess1() {
		System.out.println("ExceptionTest_test1_Thread Id :"+Thread.currentThread().getId());
		throw new RuntimeException();
	}
	@Test(expectedExceptions = RuntimeException.class)
	public void runTimeExceptionSuccess2() {
		System.out.println("ExceptionTest_test2_Thread Id :"+Thread.currentThread().getId());
		throw new RuntimeException();
	}
}
