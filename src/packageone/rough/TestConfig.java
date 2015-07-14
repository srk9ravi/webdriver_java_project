package packageone.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestConfig {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Properties CONFIG = new Properties();
		FileInputStream fipconfig = new FileInputStream(System.getProperty("user.dir")+"/bin/com/javawebdriver/config/config.properties");
		CONFIG.load(fipconfig);
		System.out.println(CONFIG.getProperty("testSiteName"));
		
		Properties OR = new Properties();
		FileInputStream fipor = new FileInputStream(System.getProperty("user.dir")+"/bin/com/javawebdriver/config/OR.properties");
		CONFIG.load(fipor);
		System.out.println(CONFIG.getProperty("user_name"));
		
		
		
	}
}
