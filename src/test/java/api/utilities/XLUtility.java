package api.utilities;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {

    private String path;
    private FileInputStream fi;
    private FileOutputStream fo;
    private XSSFWorkbook workbook;

    public XLUtility(String path) {
        this.path = path;
    }

    public void openWorkbook() throws IOException {
        if (workbook == null) {
            fi = new FileInputStream(path);
            workbook = new XSSFWorkbook(fi);
        }
    }

    public void closeWorkbook() throws IOException {
        if (workbook != null) {
            workbook.close();
            workbook = null;
        }
        if (fi != null) {
            fi.close();
            fi = null;
        }
    }

    public int getRowCount(String sheetName) throws IOException {
        openWorkbook();
        XSSFSheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) return 0;
        return sheet.getLastRowNum();
    }

    public int getCellCount(String sheetName, int rownum) throws IOException {
        openWorkbook();
        XSSFSheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) return 0;
        XSSFRow row = sheet.getRow(rownum);
        if (row == null) return 0;
        return row.getLastCellNum();
    }

    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
        openWorkbook();
        XSSFSheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) return "";
        XSSFRow row = sheet.getRow(rownum);
        if (row == null) return "";
        XSSFCell cell = row.getCell(colnum);
        if (cell == null) return "";

        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }

    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
        openWorkbook();

        XSSFSheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
        }

        XSSFRow row = sheet.getRow(rownum);
        if (row == null) {
            row = sheet.createRow(rownum);
        }

        XSSFCell cell = row.getCell(colnum);
        if (cell == null) {
            cell = row.createCell(colnum);
        }

        cell.setCellValue(data);
    }

    public void saveWorkbook() throws IOException {
        if (workbook == null) return;

        fo = new FileOutputStream(path);
        workbook.write(fo);
        fo.close();
    }

    public void closeResources() throws IOException {
        closeWorkbook();
        if (fo != null) {
            fo.close();
            fo = null;
        }
    }
}

