/*package com.javasampleapproach.twitterbootstrap.utility.xls;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelExporter implements Exporter {
	private Workbook workbook;
	private Sheet sheet;
	private short rowIndex = 0;
	private Row row;

	public ExcelExporter(String sheetName) {
		workbook = createWorkbook();
		sheet = createSheet(workbook, sheetName);
	}

	public String contentType() {
		return "application/vnd.ms-excel";
	}

	public String fileExtension() {
		return ".xls";
	}

	public void exportHeader(OutputStream outputStream, List<String> header)
			throws IOException {
		writeRow(outputStream, header);
	}

	public void exportRow(OutputStream outputStream, List<String> row)
			throws IOException {
		writeRow(outputStream, row);
	}

	private void writeRow(OutputStream outputStream, List<String> rowData)
			throws IOException {
		Row row = createRow(sheet, rowIndex);
		//int columns = row.size();

		for (int index = 0; index < columns; index++) {
			String data = rowData.get(index);
			Cell cell = createCell(row, index);
			cell.setCellValue(data);
		}
		rowIndex++;
	}

	protected Workbook createWorkbook() {
		return new HSSFWorkbook();
	}

	protected Sheet createSheet(Workbook workbook, String sheetName) {
		return workbook.createSheet(sheetName);
	}

	protected Row createRow(Sheet sheet, short rowIndex) {
		return sheet.createRow(rowIndex);
	}

	protected Cell createCell(Row row, int columnIndex) {
		return row.createCell(columnIndex);
	}

	public void flush(OutputStream outputStream) throws IOException {
		workbook.write(outputStream);
	}

	public Row getCurrentRow() {
		return row;
	}

	public Workbook getCurrentWorkbook() {
		return workbook;
	}

}
*/