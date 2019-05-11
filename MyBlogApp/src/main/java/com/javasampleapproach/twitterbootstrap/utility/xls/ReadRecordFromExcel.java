package com.javasampleapproach.twitterbootstrap.utility.xls;



import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

//How to read excel files using Apache POI
public class ReadRecordFromExcel {
	
    public static final String EXCEL_FILE_LOCATION = "E:/REPORTS/report.xls"; 

    
	public static void main (String [] args) throws IOException{
                        //I have placed an excel file 'Test.xlsx' in my D Driver 
			FileInputStream fis = new FileInputStream(EXCEL_FILE_LOCATION);
			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			HSSFSheet sheet = workbook.getSheetAt(0);
                        //I have added test data in the cell A1 as "SoftwareTestingMaterial.com"
                        //Cell A1 = row 0 and column 0. It reads first row as 0 and Column A as 0.
			Row row = sheet.getRow(0);
			Cell cell = row.getCell(0);
                       	System.out.println(cell);
			System.out.println(sheet.getRow(0).getCell(0));
			//String cellval = cell.getStringCellValue();
			//System.out.println(cellval);
			
	}		
}
