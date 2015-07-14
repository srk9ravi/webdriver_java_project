package packageone.rough;

import com.javawebdriver.util.Xls_Reader;

public class SuiteRunmode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		System.out.println(System.getProperty("user.dir"));
		Xls_Reader xlsfile = new Xls_Reader(System.getProperty("user.dir")+ "/bin/com/javawebdriver/xls/Suite.xlsx");
		System.out.println(isSuiteRunnable(xlsfile, "Shop Suite"));
	}

	
	// to get the run mode of the suite we passed
	public static boolean isSuiteRunnable(Xls_Reader xls, String Sutiename){
			boolean isSuiteExecutable = false;
		for(int i=2;i<=xls.getRowCount("Test Suite");i++){
			String suite_name = xls.getCellData("Test Suite", "TSID", i);
			String run_mode = xls.getCellData("Test Suite", "Runmode", i);
			
			if(suite_name.equalsIgnoreCase(Sutiename)){
				if(run_mode.equalsIgnoreCase("Y")){
					isSuiteExecutable=true;		
				}	
			}	
		}
		xls = null; //to release the memory
		return isSuiteExecutable;
	}

}

	
	

