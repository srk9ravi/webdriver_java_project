package com.javawebdriver.suiteA;

import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.*;

import com.javawebdriver.base.TestBase;
import com.javawebdriver.util.TestUtil;

public class TestSuiteBase extends TestBase {
	
	// check if the suite is runnable
	@BeforeSuite
	public void checkSuiteSkip() throws Exception{
		initialize();
		APP_LOGS.debug("Checking Runmode of Shop Suite");
		if(!TestUtil.isSuiteRunnable(suiteXls, "Shop Suite")){
			APP_LOGS.debug("Skipped Shop Suite as the runmode was set to NO");
			throw new SkipException("RUnmode of Shop Suite set to no. So Skipping all tests in Suite A");
		}
		
	}

}
