package packageone.rough;

import com.javawebdriver.util.Xls_Reader;

public class UpdateResult_DataSet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Xls_Reader xlsfile = new Xls_Reader(System.getProperty("user.dir")+ "/bin/com/javawebdriver/xls/A Suite.xlsx");
		reportDataSetResult(xlsfile, "Testcase_A1", 2, "Pass");

	}
	
	//to update the result in the Results column
	public static void reportDataSetResult(Xls_Reader xls, String testCaseName, int rowNum, String result){
			System.out.println(result + " " + testCaseName + " " + xls + " " + rowNum );
		  xls.setCellData(testCaseName, "Results", rowNum,result);
		
	}

}
