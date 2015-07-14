package com.javawebdriver.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.javawebdriver.util.ErrorUtil;
import com.javawebdriver.util.Xls_Reader;

public class TestBase {
	
	public static Logger APP_LOGS = null;
	public static Properties CONFIG = null;
	public static Properties OR=null;
	public static Xls_Reader suiteXls = null;
	public static Xls_Reader suite_shop_xls = null;
	public static boolean isInitialized = false;
	public static boolean isBrowserOpened=false;
	public static Hashtable<String,String> sessionData = new Hashtable<String,String>();
	public static WebDriver driver =null;	
	public static boolean isInitalized=false;
	
	// responsible for initializing the base things for the execution
	// logs, config, excel files, webdriver, 
	public void initialize() throws IOException {
		// logs
		if(!isInitialized){
		APP_LOGS = Logger.getLogger("devpinoyLogger"); 
		APP_LOGS.debug("Loading Properties file and OR file");
		APP_LOGS.debug("Started the test");
		
		// loding config properties file
		CONFIG = new Properties();
		FileInputStream fipconfig = new FileInputStream(System.getProperty("user.dir")+"//bin//com//javawebdriver//config//config.properties");
		CONFIG.load(fipconfig);
		APP_LOGS.debug("Loaded the properties file");
//		System.out.println(CONFIG.getProperty("testSiteName"));
		
		// loading OR file 
		OR = new Properties();
		FileInputStream fipor = new FileInputStream(System.getProperty("user.dir")+"//bin//com//javawebdriver//config//OR.properties");
		OR.load(fipor);
		APP_LOGS.debug("Loaded the OR file");
//		System.out.println(CONFIG.getProperty("user_name"))
	
		// excel file
		suiteXls = new Xls_Reader(System.getProperty("user.dir") + "/bin/com/javawebdriver/xls/Suite.xlsx");
		suite_shop_xls = new Xls_Reader(System.getProperty("user.dir") + "/bin/com/javawebdriver/xls/Shop Suite.xlsx");
		APP_LOGS.debug("Loaded the Excel files");
		isInitalized=true;
	}	
	}
	
	public void openBrowser(){
		System.out.println("Launching the Broswer");
		if(!isBrowserOpened){
		if(CONFIG.getProperty("browserType").equals("MOZILLA"))
		    driver = new FirefoxDriver();
		APP_LOGS.debug("Browser opened");
		isBrowserOpened=true;
		String waitTime=CONFIG.getProperty("default_implicitWait");
		driver.manage().timeouts().implicitlyWait(Long.parseLong(waitTime), TimeUnit.SECONDS);

		}
	}
	
	
	public void closeBrowser(){
		driver.quit();
		APP_LOGS.debug("Browser closed");
		isBrowserOpened=false;
		}
	
	// compare titles
		public boolean compareTitle(String expectedVal){
			try{
				Assert.assertEquals(driver.getTitle() , expectedVal);
				}catch(Throwable t){
					ErrorUtil.addVerificationFailure(t);			
					APP_LOGS.debug("Titles do not match");
					return false;
				}
			return true;
		}
		
		// compare titles
				public boolean compareNumbers(int expectedVal, int actualValue){
					try{
						Assert.assertEquals(actualValue,expectedVal);
						}catch(Throwable t){
							ErrorUtil.addVerificationFailure(t);			
							APP_LOGS.debug("Values do not match");
							return false;
						}
					return true;
				}
		
		
	// to verify the element present or not
	public boolean checkElementPresence(String xpathKey){
		int count =driver.findElements(By.xpath(OR.getProperty(xpathKey))).size();
		try{
		Assert.assertTrue(count>0, "No element present");
		}catch(Throwable t){
		ErrorUtil.addVerificationFailure(t);
		APP_LOGS.debug("No element present");
		return false;
		}
		return true;
		}
	
	
	// getObjectByxpath(String id)
	public WebElement getObject(String xpathKey){
	try{
	WebElement x = driver.findElement(By.xpath(OR.getProperty(xpathKey)));
	return x;
	}
	catch(Throwable t){
	// report error
	ErrorUtil.addVerificationFailure(t);
	APP_LOGS.debug("Cannot find object with key -- " +xpathKey);
	return null;
	}
	}
	
	
	// we can write our own login method here
	public boolean login(String username, String password){
			// log u in
			return true;
	}
			
	// we can write our own logout method here
	public void logout(){
		// logout
	}
			
	// to capture the screenshot	
	public void capturescreenshot(String filename) throws IOException{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\screenshots\\"+filename+".jpg"));
		    }
	
}
