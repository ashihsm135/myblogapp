package com.javasampleapproach.twitterbootstrap.utility.email.dao;

import java.io.File;

import com.javasampleapproach.twitterbootstrap.utility.email.action.Mail;

public class EmailDAO {
	/**
	 * This Function Return EmailTemplet.
	 * 
	 * @param type
	 * @return
	 */

	public static boolean sendMailSafetyAlertReport(File file,
			String startDate, String endDate, StringBuffer bodyContent) {
		boolean mailStatus = false;
		String[] to = new String[1];
		String[] bcc = new String[1];
		to[0] = "ashishm135@gmail.com";
		bcc[0] = "ashishm135@gmail.com";
		String subject = "Test Report";
		String content = bodyContent.toString();

		mailStatus = Mail
				.sendMail(to, bcc, subject, content, file, "text/html");
		return mailStatus;
	}

	public static void main(String[] args) {
		
		 String FILENAME = "C:/Users/Lenovo/Desktop/reports/genericpdf.pdf";

		StringBuffer sb = new StringBuffer();
		File file = new File(FILENAME);
		sb.append("Test mail");
		sendMailSafetyAlertReport(file, null, null, sb);

	}
}