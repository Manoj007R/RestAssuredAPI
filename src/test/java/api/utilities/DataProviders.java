package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="Data")
	public String[][] getalldata() throws IOException {
		String path = System.getProperty("user.dir")+"//testdata//UserData.xlsx";
		XLUtility xl = new XLUtility();
		
		int rowcount = xl.getRowCount(path, "sheet1");
		int columncount = xl.getCellCount(path, "sheet1", 1);
		String apidata[][] = new String[rowcount-1][columncount];	
		for(int i=1; i<rowcount; i++) {	
			for(int j=0; j<columncount;j++) {
				apidata[i-1][j] = xl.getCellData(path, "sheet1", i, j);
			}
		}
		return apidata;
	}
	
	@DataProvider(name="UserNames")
	public String[] getUserNames() throws IOException {
		String path = System.getProperty("user.dir")+"//testdata//UserData.xlsx";
		XLUtility xl = new XLUtility();
		
		int rowcount = xl.getRowCount(path, "sheet1");
		String apidata[] = new String[rowcount-1];
		for(int i=1; i<rowcount; i++) {
		apidata[i-1] = xl.getCellData(path, "sheet1", i, 1);
	}
		return apidata;
	}
}
