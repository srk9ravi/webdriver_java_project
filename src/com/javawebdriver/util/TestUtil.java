package com.javawebdriver.util;



public class TestUtil {
	
	// to get the run mode of the suite for the specified suite
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
	
	// to get the run mode of the test case we passed
	public static boolean isTestcaseRunnable(Xls_Reader xls, String TestcaseName){
			boolean isTestcaseRunnable = false;
		for(int i=2;i<=xls.getRowCount("Test cases");i++){
			String testcase_name = xls.getCellData("Test cases", "TCID", i);
			String run_mode = xls.getCellData("Test cases", "Runmode", i);
			 
			
			if(testcase_name.equalsIgnoreCase(TestcaseName)){
				System.out.println(testcase_name);
				System.out.println("Runmode of the testcase is:- "  + run_mode);
				if(run_mode.equalsIgnoreCase("Y")){
					isTestcaseRunnable=true;		
				}	
			}	
		}
		xls = null; //to release the memory
		return isTestcaseRunnable;
	}
	
	//return test data from a two dimentional array
	public static Object[][] getData(Xls_Reader xls, String testCaseName) {	
			//verify for the test case data sheet existance
		if(!xls.isSheetExist(testCaseName)){
			xls = null;
			System.out.println("passed");
			return new Object[1][0];
		}
		int rows = xls.getRowCount(testCaseName);
		int columns = xls.getColumnCount(testCaseName);
		Object[][] data = new Object[rows-1][columns-3];
		System.out.println("Test data from the excel sheet");
		for(int rowNum=2;rowNum<=rows;rowNum++){
			for(int colNum=0;colNum<=columns-4;colNum++){
				System.out.println(xls.getCellData(testCaseName, colNum, rowNum));
				data[rowNum-2][colNum] = xls.getCellData(testCaseName, colNum, rowNum);
//				System.out.println(data);
			}
		}
		return data;
	}
	

	// checks RUnmode for dataSet
	public static String[] getDataSetRunmodes(Xls_Reader xlsFile,String sheetName){
		String[] runmodes=null;
		if(!xlsFile.isSheetExist(sheetName)){
			xlsFile=null;
			sheetName=null;
			runmodes = new String[1];
			runmodes[0]="Y";
			xlsFile=null;
			sheetName=null;
			return runmodes;
		}
		runmodes = new String[xlsFile.getRowCount(sheetName)-1];
		for(int i=2;i<=runmodes.length+1;i++){
			runmodes[i-2]=xlsFile.getCellData(sheetName, "Runmode", i);
		}
		xlsFile=null;
		sheetName=null;
		return runmodes;
	}
	
	// return the row num for a test
	public static int getRowNum(Xls_Reader xls, String id){
		for(int i=2; i<= xls.getRowCount("Test Cases") ; i++){
			String tcid=xls.getCellData("Test Cases", "TCID", i);
			if(tcid.equals(id)){
				xls=null;
				return i;
			}	
		}	
		return -1;
	}

	
	//to update the result in the Results column
	public static void reportDataSetResult(Xls_Reader xls, String testCaseName, int rowNum, String result){
		xls.setCellData(testCaseName, "Results", rowNum,result);
	}
	
}
