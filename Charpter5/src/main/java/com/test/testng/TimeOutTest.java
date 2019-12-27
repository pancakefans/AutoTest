package com.test.testng;

import org.testng.annotations.Test;

public class TimeOutTest {
	@Test(timeOut = 3000)
	public void timeOutfail() throws InterruptedException {
		Thread.sleep(4000);
	}

}
