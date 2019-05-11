package com.javasampleapproach.twitterbootstrap.utility.pdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFLandScape {
	 
	        public static void main(String args[]) throws MalformedURLException, IOException, DocumentException
	        {
	        Document document = new Document(PageSize.A4.rotate(), 40f, 10f, 50f, 10f);
	        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/Users/Ashish Mishra/Desktop/reports/test.pdf"));
	        document.open();
	        document.newPage();
	        Image img = Image.getInstance(String.format("C:/Users/Ashish Mishra/Desktop/reports/1526970576329_AS_Cert.png"));
	        img.scaleToFit(2000,900);
	        document.left(100f);
	        document.top(150f);
	        document.add(img);
	        document.close();
	        
	        }
	}
