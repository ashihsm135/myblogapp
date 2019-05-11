package com.javasampleapproach.twitterbootstrap.utility.pdf;

import com.itextpdf.text.Font;


public class PDFReportColumn {

	private String	m_method;
	private String	m_header;
	private Font	m_font;
	private Short	m_color;



	public PDFReportColumn(String method, String header, Font font, Short color) {

		this.m_method = method;
		this.m_header = header;
		this.m_font = font;
		this.m_color = color;
	}



	public PDFReportColumn(String method, String header, Font font) {

		this(method, header, font, null);
	}



	public PDFReportColumn(String method, String header, Short color) {

		this(method, header, null, color);
	}



	public PDFReportColumn(String method, String header) {

		this(method, header, null, null);
	}



	public String getMethod() {

		return m_method;
	}



	public void setMethod(String method) {

		this.m_method = method;
	}



	public String getHeader() {

		return m_header;
	}



	public void setHeader(String header) {

		this.m_header = header;
	}



	public Font getFont() {

		return m_font;
	}



	public void setFont(Font m_font) {

		this.m_font = m_font;
	}



	public Short getColor() {

		return m_color;
	}



	public void setColor(Short m_color) {

		this.m_color = m_color;
	}
}
