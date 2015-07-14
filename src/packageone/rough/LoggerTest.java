package packageone.rough;

import org.apache.log4j.Logger;

public class LoggerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Logger APP_LOGS = Logger.getLogger("seleniumjavalogger"); 	
		APP_LOGS.debug("correct");
			
	}
}
