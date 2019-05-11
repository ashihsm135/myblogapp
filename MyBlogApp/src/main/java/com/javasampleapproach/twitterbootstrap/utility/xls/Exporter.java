package com.javasampleapproach.twitterbootstrap.utility.xls;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public interface Exporter {

	String contentType();

	String fileExtension();

	void exportHeader(OutputStream outputStream, List<String> header)
			throws IOException;

	void exportRow(OutputStream outputStream, List<String> row)
			throws IOException;

	void flush(OutputStream outputStream) throws IOException;

}
