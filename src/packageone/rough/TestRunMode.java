package packageone.rough;

import com.javawebdriver.util.Xls_Reader;

public class TestRunMode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		System.out.println(System.getProperty("user.dir"));
		System.out.println(System.getProperty("user.dir")+ "/bin/com/javawebdriver/xls/A Suite.xlsx");
		Xls_Reader xlsfile = new Xls_Reader(System.getProperty("user.dir")+ "/bin/com/javawebdriver/xls/A Suite.xlsx");
		System.out.println(isTestcaseRunnable(xlsfile, "AddProductTest"));
		
//		Xls_Reader xlsfile2 = new Xls_Reader(System.getProperty("user.dir")+ "/bin/com/javawebdriver/xls/B Suite.xlsx");
//		System.out.println(isTestcaseRunnable(xlsfile2, "Testcase_B1"));
	}
	
	// to get the run mode of the test case we passed
	public static boolean isTestcaseRunnable(Xls_Reader xls, String TestcaseName){
			boolean isTestcaseRunnable = false;
		for(int i=2;i<=xls.getRowCount("Test cases");i++){
			String testcase_name = xls.getCellData("Test cases", "TCID", i);
			String run_mode = xls.getCellData("Test cases", "Runmode", i);
			 
			if(testcase_name.equalsIgnoreCase(TestcaseName)){
				System.out.println(testcase_name);
				if(run_mode.equalsIgnoreCase("Y")){
					isTestcaseRunnable=true;		
				}	
			}	
		}
		xls = null; //to release the memory
		return isTestcaseRunnable;
	}

}
