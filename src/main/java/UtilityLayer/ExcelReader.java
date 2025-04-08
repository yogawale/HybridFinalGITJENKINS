package UtilityLayer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public FileInputStream fis;
	public FileOutputStream fos;
	String path = null;
	
	public ExcelReader(String path){
		this.path = path;
	}
	
	public int getRowCount(String sheetname) throws IOException {
		
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(0);
		int rowcount = sheet.getLastRowNum();
		workbook.close();
		fis.close();
		return rowcount;
	}
	
	public int getCellCount(String sheetname, int rowcount) throws IOException {
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(0);
		row = sheet.getRow(rowcount);
		int colcount = row.getLastCellNum();
		workbook.close();
		fis.close();
		return colcount;
	}
	
	public String getCellData(String sheetname, int rowcount, int colcount)throws IOException {
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(0);
		row = sheet.getRow(rowcount);
		cell = row.getCell(colcount);
		
		DataFormatter format = new DataFormatter();
		String data;
		
		data = format.formatCellValue(cell);
		
		workbook.close();
		fis.close();
		return data;
	}
	
}
