package com.javasampleapproach.twitterbootstrap.utility.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfCreationTest {

	public static final String	DEST	= "C:/Users/Ashish Mishra/Desktop/reports/orientations.pdf";

	public class Rotate extends PdfPageEventHelper {

		protected PdfNumber	rotation	= PdfPage.LANDSCAPE;



		public void setRotation(PdfNumber rotation) {

			this.rotation = rotation;
		}



		@Override
		public void onEndPage(PdfWriter writer, Document document) {

			writer.addPageDictEntry(PdfName.ROTATE, rotation);
		}
	}



	public static void main(String[] args) throws IOException,
			DocumentException {

		File file = new File(DEST);
		file.getParentFile().mkdirs();
		new PdfCreationTest().createPdf(DEST);
	}



	public void createPdf(String filename) throws IOException,
			DocumentException {

		Document document = new Document();
		document.setMargins(1, 1, 1, 1);
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream(filename));
		Rotate rotation = new Rotate();
		writer.setPageEvent(rotation);

		String imageUrl = "C:/Users/Ashish Mishra/Desktop/reports/1522674021624_AS_Cert.PNG";
		Image image = Image.getInstance(imageUrl);
		document.open();
		document.add(image);
		document.close();
		
	}
}
