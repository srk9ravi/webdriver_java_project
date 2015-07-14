
package packageone.rough;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

//import com.qtpselenium.util.Xls_Reader;
public class sample_webdriver {
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new FirefoxDriver();	
		driver.get("http://www.cnn.com");
		
		Thread.sleep(4000);
		driver.quit();	
	}
}

//String xl_path = "/Users/lch735/Desktop/Ravi/project work/Selenium Java/data/Suite.xlsx";
//Xls_Reader xlread = new Xls_Reader(xl_path);
//System.out.println(xlread.getRowCount("Test Suite"));

