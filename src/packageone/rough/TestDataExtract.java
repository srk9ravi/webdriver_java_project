package packageone.rough;
import com.javawebdriver.util.Xls_Reader;

public class TestDataExtract {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Xls_Reader xlsfile = new Xls_Reader(System.getProperty("user.dir")+ "/bin/com/javawebdriver/xls/A Suite.xlsx");
		System.out.println(getData(xlsfile,"AddProductTest"));
	}
	
	//retrun test data from a two dimentional array
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
		for(int rowNum=2;rowNum<=rows;rowNum++){
			for(int colNum=0;colNum<=columns-4;colNum++){
				System.out.println(xls.getCellData(testCaseName, colNum, rowNum));
				data[rowNum-2][colNum] = xls.getCellData(testCaseName, colNum, rowNum);
//				System.out.println(data);
			}
		}
		return data;
	}
}
