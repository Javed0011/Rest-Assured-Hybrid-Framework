package utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "Data")
    public Object[][] getAllData() throws IOException {
        
        String path = System.getProperty("user.dir") + "//TestData//userdata.xlsx";
        XLUtility xl = new XLUtility(path);

        
        int rownum = xl.getRowCount("Sheet1");
        int colcount = xl.getCellCount("Sheet1", 1);
        
        Object[][] apidata = new Object[rownum][colcount];
        for (int i = 1; i <= rownum; i++) {
            for (int j = 0; j < colcount; j++) {
                apidata[i - 1][j] = xl.getCellData("Sheet1", i, j);
            }
        }
        return apidata;
    }

    @DataProvider(name = "UserNames")
    public Object[] getUserNames() throws IOException {
        
        String path = System.getProperty("user.dir") + "//TestData//userdata.xlsx";
        XLUtility xl = new XLUtility(path);

       
        int rownum = xl.getRowCount("Sheet1");
        Object[] apidata = new Object[rownum];
        for (int i = 1; i <= rownum; i++) {
            apidata[i - 1] = xl.getCellData("Sheet1", i, 1); // Column index 1 (UserName)
        }

       return apidata;
    }
}
