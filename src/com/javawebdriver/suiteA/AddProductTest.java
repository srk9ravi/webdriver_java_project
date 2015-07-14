package com.javawebdriver.suiteA;


import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;

import com.javawebdriver.util.TestUtil;

public class AddProductTest extends TestSuiteBase{
	String runmodes[]=null;
	static boolean fail=false;
	static boolean skip=false;
	static boolean isTestPass=true;
	static int count=-1;
	// Runmode of test case in a suite
		@BeforeTest
		public void checkTestSkip(){
			if(!TestUtil.isTestcaseRunnable(suite_shop_xls,this.getClass().getSimpleName())){
				APP_LOGS.debug("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//logs
				throw new SkipException("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//reports
			}
			runmodes=TestUtil.getDataSetRunmodes(suite_shop_xls, this.getClass().getSimpleName());
		}
	
	@Test(dataProvider="getTestData")
	public void addProductToBasket(
			String mobileName,
			String color,
			String memory,
			String engraving,
			String quantity
			) throws InterruptedException, IOException{
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		// starting webdriver with firefox browser
		System.out.println("Starting WebDriver");
		openBrowser();
		
		driver.get(CONFIG.getProperty("testSiteName"));

		getObject("account_tab").click();
		String your_account_text = getObject("your_account_details").getText();
		System.out.println("your account text displayed is: " + your_account_text);
		Assert.assertEquals("Your account details", your_account_text);
		checkElementPresence("your_account_details");
		
	
		
		closeBrowser();
	}
	
	
	@AfterMethod
	public void reportDataSetResult(){
		if(skip)
			TestUtil.reportDataSetResult(suite_shop_xls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			isTestPass=false;
			TestUtil.reportDataSetResult(suite_shop_xls, this.getClass().getSimpleName(), count+2, "FAIL");
		}
		else
			TestUtil.reportDataSetResult(suite_shop_xls, this.getClass().getSimpleName(), count+2, "PASS");
		
		skip=false;
		fail=false;

	}
	
	@AfterTest
	public void reportTestResult(){
		if(isTestPass)
			TestUtil.reportDataSetResult(suite_shop_xls, "Test Cases", TestUtil.getRowNum(suite_shop_xls,this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(suite_shop_xls, "Test Cases", TestUtil.getRowNum(suite_shop_xls,this.getClass().getSimpleName()), "FAIL");
	
	}
	

	@DataProvider
	public Object[][] getTestData(){
		return TestUtil.getData(suite_shop_xls, this.getClass().getSimpleName()) ;
	}
}
