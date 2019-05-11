package com.javasampleapproach.twitterbootstrap.utility.pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.javasampleapproach.twitterbootstrap.utility.Constant;

public class BeenToPdf {

	private static Document	document;
	private Paragraph		header;
	private Paragraph		footer;
	private PdfPTable		table;
	private PdfPCell		cell;
	private Font			font;
	private Font			headingFont;



	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy, MM, dd");
		try {
			
			File file = new File(Constant.REAL_PATH + File.separator + "genericpdf.pdf");
			BeenToPdf oReport = new BeenToPdf();

			List<Employee> employees = new ArrayList<Employee>();
			employees.add(new Employee(100, "Abe Adams", sdf
					.parse("2009, 12, 1"), 10000.00));
			employees.add(new Employee(101, "Betty Barnes", sdf
					.parse("2010, 11, 1"), 11000.00));
			employees.add(new Employee(102, "Caleb Crown", sdf
					.parse("2011, 10, 1"), 12000.00));
			employees.add(new Employee(103, "Dirk Daniels", sdf
					.parse("2012, 09, 1"), 13000.00));
			float[] collums = { 2, 3, (float) 3.5, 2 };

			PDFReportColumn[] reportColumns = new PDFReportColumn[] {
					new PDFReportColumn("id", "Id"),
					new PDFReportColumn("name", "Last Name"),
					new PDFReportColumn("hireDate", "Hire Date"),
					new PDFReportColumn("salary", "Salary") };

			oReport.addDocument(employees, reportColumns, "Document1", collums,
					file);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public BeenToPdf() throws FileNotFoundException, DocumentException {

		document = new Document(PageSize.A4_LANDSCAPE, 10.0F, 10.0F, 30.0F, 30.0F);
		font = FontFactory.getFont("Helvetica", 7.0F);
		headingFont = FontFactory.getFont("Helvetica", 10.0F);
	}



	public void addHeaderString(PdfPTable table, int currentRow,
			ReportColumn[] columns, Font headingFont) {

		table.addCell(new Phrase("Status", headingFont));
		table.addCell(new Phrase("Asset", headingFont));
		table.addCell(new Phrase("DateTime", headingFont));
		table.addCell(new Phrase("Speed(KM)", headingFont));
		table.addCell(new Phrase("Address", headingFont));
		table.addCell(new Phrase("Heading", headingFont));
	}



	public void addDocument(List<?> data, PDFReportColumn[] columns,
			String documentname, float[] collums, File file) {

		int numCols = columns.length;
		String title = "Threshold.Inc";
		int colspan = columns.length;
		try {
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream(file));
			document.open();
			addHeader(title);

			addTable(collums);
			addCell("Report for Threshold Fleet", colspan);
			// addCell("Period From ",colspan);

			for (int i = 0; i < numCols; i++) {
				writeCell(table, columns[i].getHeader(), this.headingFont);
			}

			for (int i = 0; i < data.size(); i++) {
				Object bean = data.get(i);
				for (int y = 0; y < numCols; y++) {
					Object value = PropertyUtils.getProperty(bean,
							columns[y].getMethod());
					writeCell(table, value.toString(), columns[y].getFont());
				}
			}
			table.completeRow();
			document.add(table);
			addFooter();
			document.close();
		} catch (Exception e) {
			System.err.println("Caught Generate Error exception: "
					+ e.getMessage());
		}
	}



	public Font boldFont() {

		return font;
	}



	@SuppressWarnings("static-access")
	public void write(PdfWriter writer, Document document, File file)
			throws Exception {

		writer.getInstance(document, new FileOutputStream(file));
	}



	private void writeCell(PdfPTable table, String value, Font font)
			throws Exception {

		table.addCell(new Phrase(value, headingFont));
		if (value == null) {
			return;
		}
	}



	private void addCell(String message, int colspan) throws Exception {

		cell = new PdfPCell(new Paragraph(message));
		cell.setColspan(colspan);
		cell.setHorizontalAlignment(0);
		table.addCell(cell);
		table.completeRow();
	}



	private void addTable(float[] collums) throws Exception {

		table = new PdfPTable(collums);
		table.setWidthPercentage(100);
		table.getDefaultCell().setUseAscender(true);
		table.getDefaultCell().setUseDescender(true);
	}



	private void addHeader(String title) throws Exception {

		header = new Paragraph("Threshold.Inc");
		header.setSpacingAfter(10.0F);
		document.add(header);
		document.add(new LineSeparator());
		document.add(new Paragraph(" "));
	}



	private void addFooter() throws Exception {

		document.add(new Paragraph(" "));
		document.add(new LineSeparator());
		footer = new Paragraph("Powered by Threshold Inc.");
		footer.setSpacingBefore(5.0F);
		footer.setSpacingAfter(5.0F);
		footer.setAlignment(1);
		document.add(footer);
	}
}
