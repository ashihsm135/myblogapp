package com.javasampleapproach.twitterbootstrap.utility.email.action;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mail {

	/**
	 * 
	 * @param to
	 * @param subject
	 * @param text
	 * @return
	 */
	public synchronized static boolean sendMail(String[] to, String subject, String text){
		return sendMail("fleetinfo.ca@gmail.com", "avibha1234", "smtp.gmail.com",
				"465", "true", "true", true, "javax.net.ssl.SSLSocketFactory",
				"false", to,  subject,
				text);		
	}
	
	/**
	 * 
	 * @param to
	 * @param subject
	 * @param text
	 * @param files
	 * @param type
	 * @return
	 */
	public synchronized static boolean sendMail(String[] to, String subject, String text, File[] files, String type){
		return sendMail("fleetinfo.ca@gmail.com", "avibha1234", "smtp.gmail.com",
				"465", "true", "true", true, "javax.net.ssl.SSLSocketFactory",
				"false", to,  subject,
				text, files,type);
	}
	
	/**
	 * 
	 * @param userName
	 * @param passWord
	 * @param host
	 * @param port
	 * @param starttls
	 * @param auth
	 * @param debug
	 * @param socketFactoryClass
	 * @param fallback
	 * @param to
	 * @param subject
	 * @param text
	 * @param files
	 * @param type
	 * @return
	 */
	private static boolean sendMail(String userName,
			String passWord, String host, String port, String starttls,
			String auth, boolean debug, String socketFactoryClass,
			String fallback, String[] to, 
			String subject, String text, File[] files, String type) {
		
		Properties props = new Properties();		
		props.put("mail.smtp.user", userName);
		props.put("mail.smtp.host", host);
		
		if (!"".equals(port))
			props.put("mail.smtp.port", port);
		if (!"".equals(starttls))
			props.put("mail.smtp.starttls.enable", starttls);
		
		props.put("mail.smtp.auth", auth);
		
		if (debug) {
			props.put("mail.smtp.debug", "true");
		} else {
			props.put("mail.smtp.debug", "false");
		}
		if (!"".equals(port))
			props.put("mail.smtp.socketFactory.port", port);
		if (!"".equals(socketFactoryClass))
			props.put("mail.smtp.socketFactory.class", socketFactoryClass);
		if (!"".equals(fallback))
			props.put("mail.smtp.socketFactory.fallback", fallback);

		try {
			Session session = Session.getDefaultInstance(props, null);
			session.setDebug(debug);
			
			MimeMessage msg = new MimeMessage(session);
			
			// Create the message part 
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(text, "text/html");
			
			MimeMultipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

//			msg.setContent(text, "text/html");
//			msg.setText(text);
			msg.setSubject(subject);
			msg.setFrom(new InternetAddress("noreply@trimtracgps.ca"));			
			
			for (int i = 0; i < to.length; i++) {
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
			}
			msg.addRecipient(Message.RecipientType.BCC, new InternetAddress("deepak@avibha.com"));	
					
			if (files != null && files.length > 0) {
				for (File file : files) {
					if (file != null) {
						MimeBodyPart bodyPart = new MimeBodyPart();
						bodyPart.setContent(file, type);
						FileDataSource fds = new FileDataSource(file);
						bodyPart.setDataHandler(new DataHandler(fds));
						bodyPart.setFileName(fds.getName());
						multipart.addBodyPart(bodyPart);						
					}
				}
			}
			msg.setContent(multipart);
			
			msg.saveChanges();
			
			Transport transport = session.getTransport("smtp");
			transport.connect(host, userName, passWord);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			return true;
		} catch (Exception mex) {
			mex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	 * @param to
	 * @param bcc
	 * @param subject
	 * @param text
	 * @param file
	 * @param type
	 * @return
	 */
	
	public synchronized static boolean sendMail(String[] to, String[] bcc, String subject, String text, File file, String type){
		return sendMail("mishraa228@gmail.com", "8793620940", "smtp.gmail.com",
				"465", "true", "true", true, "javax.net.ssl.SSLSocketFactory",
				"false", to, bcc,  subject,
				text, file,type);
	}
	
	/**
	 * 
	 * @param userName
	 * @param passWord
	 * @param host
	 * @param port
	 * @param starttls
	 * @param auth
	 * @param debug
	 * @param socketFactoryClass
	 * @param fallback
	 * @param to
	 * @param bcc
	 * @param subject
	 * @param text
	 * @param file
	 * @param type
	 * @return
	 */
	private static boolean sendMail(String userName,
			String passWord, String host, String port, String starttls,
			String auth, boolean debug, String socketFactoryClass,
			String fallback, String[] to, String[] bcc, 
			String subject, String text, File file, String type) {
		
		Properties props = new Properties();		
		props.put("mail.smtp.user", userName);
		props.put("mail.smtp.host", host);
		
		if (!"".equals(port))
			props.put("mail.smtp.port", port);
		if (!"".equals(starttls))
			props.put("mail.smtp.starttls.enable", starttls);
		
		props.put("mail.smtp.auth", auth);
		
		if (debug) {
			props.put("mail.smtp.debug", "true");
		} else {
			props.put("mail.smtp.debug", "false");
		}
		if (!"".equals(port))
			props.put("mail.smtp.socketFactory.port", port);
		if (!"".equals(socketFactoryClass))
			props.put("mail.smtp.socketFactory.class", socketFactoryClass);
		if (!"".equals(fallback))
			props.put("mail.smtp.socketFactory.fallback", fallback);

		try {
			Session session = Session.getDefaultInstance(props, null);
			session.setDebug(debug);
			
			MimeMessage msg = new MimeMessage(session);
			
			// Create the message part 
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(text, "text/html");
			
			MimeMultipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

//			msg.setContent(text, "text/html");
//			msg.setText(text);
			msg.setSubject(subject);
			msg.setFrom(new InternetAddress("ashishm135@gmail.com"));			
			
			for (int i = 0; i < to.length; i++) {
				if(to[i] != null && !"".equals(to[i])){
					msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
				}
				
			}
			
			for (int i = 0; i < bcc.length; i++) {
				if(bcc[i] != null && !"".equals(bcc[i])){
					msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc[i]));
				}				
			}			
			msg.addRecipient(Message.RecipientType.BCC, new InternetAddress("ashishm135@gmail.com"));	
	
			if (file != null) {
				MimeBodyPart bodyPart = new MimeBodyPart();
				bodyPart.setContent(file, type);
				FileDataSource fds = new FileDataSource(file);
				bodyPart.setDataHandler(new DataHandler(fds));
				bodyPart.setFileName(fds.getName());
				multipart.addBodyPart(bodyPart);						
			}
		
			msg.setContent(multipart);
			
			msg.saveChanges();
			
			Transport transport = session.getTransport("smtp");
			transport.connect(host, userName, passWord);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			return true;
		} catch (Exception mex) {
			mex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param subject
	 * @param text
	 * @param file
	 * @param type
	 * @return
	 */
	public synchronized static boolean sendMail(String to, String cc,String bcc, String subject, String text, File file, String type){
		return sendMail("fleetinfo.ca@gmail.com", "avibha1234", "smtp.gmail.com",
				"465", "true", "true", true, "javax.net.ssl.SSLSocketFactory",
				"false", to,cc, bcc,  subject,
				text, file,type);
	}
	
	/**
	 * 
	 * @param userName
	 * @param passWord
	 * @param host
	 * @param port
	 * @param starttls
	 * @param auth
	 * @param debug
	 * @param socketFactoryClass
	 * @param fallback
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param subject
	 * @param text
	 * @param file
	 * @param type
	 * @return
	 */
	private static boolean sendMail(String userName,
			String passWord, String host, String port, String starttls,
			String auth, boolean debug, String socketFactoryClass,
			String fallback, String to,String cc, String bcc, 
			String subject, String text, File file, String type) {
		
		Properties props = new Properties();		
		props.put("mail.smtp.user", userName);
		props.put("mail.smtp.host", host);
		
		if (!"".equals(port))
			props.put("mail.smtp.port", port);
		if (!"".equals(starttls))
			props.put("mail.smtp.starttls.enable", starttls);
		
		props.put("mail.smtp.auth", auth);
		
		if (debug) {
			props.put("mail.smtp.debug", "true");
		} else {
			props.put("mail.smtp.debug", "false");
		}
		if (!"".equals(port))
			props.put("mail.smtp.socketFactory.port", port);
		if (!"".equals(socketFactoryClass))
			props.put("mail.smtp.socketFactory.class", socketFactoryClass);
		if (!"".equals(fallback))
			props.put("mail.smtp.socketFactory.fallback", fallback);

		try {
			Session session = Session.getDefaultInstance(props, null);
			session.setDebug(debug);
			
			MimeMessage msg = new MimeMessage(session);
			
			// Create the message part 
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(text, "text/html");
			
			MimeMultipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

//			msg.setContent(text, "text/html");
//			msg.setText(text);
			msg.setSubject(subject);
			msg.setFrom(new InternetAddress("noreply@trimtracgps.ca"));			
			
			//for (int i = 0; i < to.length; i++) {
				if(to != null && !"".equals(to)){
					msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				}
				
			//}
			//for (int i = 0; i < cc.length; i++) {
				if(cc != null && !"".equals(cc)){
					msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
				}
				
			//}
			//for (int i = 0; i < bcc.length; i++) {
				if(bcc != null && !"".equals(bcc)){
					msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));
				}				
			//}			
				msg.addRecipient(Message.RecipientType.BCC, new InternetAddress("deepak@avibha.com"));	
	
			if (file != null) {
				MimeBodyPart bodyPart = new MimeBodyPart();
				bodyPart.setContent(file, type);
				FileDataSource fds = new FileDataSource(file);
				bodyPart.setDataHandler(new DataHandler(fds));
				bodyPart.setFileName(fds.getName());
				multipart.addBodyPart(bodyPart);						
			}
		
			msg.setContent(multipart);
			
			msg.saveChanges();
			
			Transport transport = session.getTransport("smtp");
			transport.connect(host, userName, passWord);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			return true;
		} catch (Exception mex) {
			mex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param subject
	 * @param text
	 * @param file
	 * @param type
	 * @return
	 */
	public synchronized static boolean sendMail(String[] to, String[] cc,String[] bcc, String subject, String text, File file, String type){
		return sendMail("mishraa228@gmail.com", "8793620940", "smtp.gmail.com",
				"465", "true", "true", true, "javax.net.ssl.SSLSocketFactory",
				"false", to,cc, bcc,  subject,
				text, file,type);
	}
	
	/**
	 * 
	 * @param userName
	 * @param passWord
	 * @param host
	 * @param port
	 * @param starttls
	 * @param auth
	 * @param debug
	 * @param socketFactoryClass
	 * @param fallback
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param subject
	 * @param text
	 * @param file
	 * @param type
	 * @return
	 */
	private static boolean sendMail(String userName,
			String passWord, String host, String port, String starttls,
			String auth, boolean debug, String socketFactoryClass,
			String fallback, String[] to,String[] cc, String[] bcc, 
			String subject, String text, File file, String type) {
		
		Properties props = new Properties();		
		props.put("mail.smtp.user", userName);
		props.put("mail.smtp.host", host);
		
		if (!"".equals(port))
			props.put("mail.smtp.port", port);
		if (!"".equals(starttls))
			props.put("mail.smtp.starttls.enable", starttls);
		
		props.put("mail.smtp.auth", auth);
		
		if (debug) {
			props.put("mail.smtp.debug", "true");
		} else {
			props.put("mail.smtp.debug", "false");
		}
		if (!"".equals(port))
			props.put("mail.smtp.socketFactory.port", port);
		if (!"".equals(socketFactoryClass))
			props.put("mail.smtp.socketFactory.class", socketFactoryClass);
		if (!"".equals(fallback))
			props.put("mail.smtp.socketFactory.fallback", fallback);

		try {
			Session session = Session.getDefaultInstance(props, null);
			session.setDebug(debug);
			
			MimeMessage msg = new MimeMessage(session);
			
			// Create the message part 
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(text, "text/html");
			
			MimeMultipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

//			msg.setContent(text, "text/html");
//			msg.setText(text);
			msg.setSubject(subject);
			msg.setFrom(new InternetAddress("noreply@trimtracgps.ca"));			
			
			for (int i = 0; i < to.length; i++) {
				if(to[i] != null && !"".equals(to[i])){
					msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
				}
				
			}
			for (int i = 0; i < cc.length; i++) {
				if(cc[i] != null && !"".equals(cc[i])){
					msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc[i]));
				}
				
			}
			for (int i = 0; i < bcc.length; i++) {
				if(bcc[i] != null && !"".equals(bcc[i])){
					msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc[i]));
				}				
			}			
			msg.addRecipient(Message.RecipientType.BCC, new InternetAddress("deepak@avibha.com"));	
	
			if (file != null) {
				MimeBodyPart bodyPart = new MimeBodyPart();
				bodyPart.setContent(file, type);
				FileDataSource fds = new FileDataSource(file);
				bodyPart.setDataHandler(new DataHandler(fds));
				bodyPart.setFileName(fds.getName());
				multipart.addBodyPart(bodyPart);						
			}
		
			msg.setContent(multipart);
			
			msg.saveChanges();
			
			Transport transport = session.getTransport("smtp");
			transport.connect(host, userName, passWord);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			return true;
		} catch (Exception mex) {
			mex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	 * @param userName
	 * @param passWord
	 * @param host
	 * @param port
	 * @param starttls
	 * @param auth
	 * @param debug
	 * @param socketFactoryClass
	 * @param fallback
	 * @param to
	 * @param subject
	 * @param text
	 * @return
	 */
	public static boolean sendMail(String userName,
			String passWord, String host, String port, String starttls,
			String auth, boolean debug, String socketFactoryClass,
			String fallback, String[] to, 
			String subject, String text) {
		Properties props = new Properties();
		// Properties props=System.getProperties();
		props.put("mail.smtp.user", userName);
		props.put("mail.smtp.host", host);
		if (!"".equals(port))
			props.put("mail.smtp.port", port);
		if (!"".equals(starttls))
			props.put("mail.smtp.starttls.enable", starttls);
		props.put("mail.smtp.auth", auth);
		if (debug) {
			props.put("mail.smtp.debug", "true");
		} else {
			props.put("mail.smtp.debug", "false");
		}
		if (!"".equals(port))
			props.put("mail.smtp.socketFactory.port", port);
		if (!"".equals(socketFactoryClass))
			props.put("mail.smtp.socketFactory.class", socketFactoryClass);
		if (!"".equals(fallback))
			props.put("mail.smtp.socketFactory.fallback", fallback);

		try {
			Session session = Session.getDefaultInstance(props, null);
			session.setDebug(debug);
			MimeMessage msg = new MimeMessage(session);
			msg.setText(text);
			msg.setSubject(subject);
			msg.setFrom(new InternetAddress("ashishm135@gmail.com"));
			for (int i = 0; i < to.length; i++) {
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
						to[i]));
			}
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					"ashishm135@gmail.com"));
//			for (int i = 0; i < cc.length; i++) {
//				msg.addRecipient(Message.RecipientType.CC, new InternetAddress(
//						cc[i]));
//			}
//			for (int i = 0; i < bcc.length; i++) {
//				msg.addRecipient(Message.RecipientType.BCC,
//						new InternetAddress(bcc[i]));
//			}
			msg.addRecipient(Message.RecipientType.BCC, new InternetAddress("ashishm135@gmail.com"));	
			msg.saveChanges();
			Transport transport = session.getTransport("smtp");
			transport.connect(host, userName, passWord);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			return true;
		} catch (Exception mex) {
			mex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param subject
	 * @param text
	 * @return
	 */
	public synchronized static boolean sendMail(String to, String cc, String bcc, String subject, String text){
		return sendMail("mishraa228@gmail.com", "8793620940", "smtp.gmail.com",
				"465", "true", "true", true, "javax.net.ssl.SSLSocketFactory",
				"false", to, cc, bcc, subject,
				text);		
	}
	
	/**
	 * 
	 * @param userName
	 * @param passWord
	 * @param host
	 * @param port
	 * @param starttls
	 * @param auth
	 * @param debug
	 * @param socketFactoryClass
	 * @param fallback
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param subject
	 * @param text
	 * @return
	 */
	private static boolean sendMail(String userName,String passWord, 
			String host, String port, String starttls,
			String auth, boolean debug, String socketFactoryClass,
			String fallback, String to, String cc, String bcc, 
			String subject, String text) {
		Properties props = new Properties();
		// Properties props=System.getProperties();
		props.put("mail.smtp.user", userName);
		props.put("mail.smtp.host", host);
		if (!"".equals(port))
			props.put("mail.smtp.port", port);
		if (!"".equals(starttls))
			props.put("mail.smtp.starttls.enable", starttls);
		props.put("mail.smtp.auth", auth);
		if (debug) {
			props.put("mail.smtp.debug", "true");
		} else {
			props.put("mail.smtp.debug", "false");
		}
		if (!"".equals(port))
			props.put("mail.smtp.socketFactory.port", port);
		if (!"".equals(socketFactoryClass))
			props.put("mail.smtp.socketFactory.class", socketFactoryClass);
		if (!"".equals(fallback))
			props.put("mail.smtp.socketFactory.fallback", fallback);

		try {
			Session session = Session.getDefaultInstance(props, null);
			session.setDebug(debug);
			MimeMessage msg = new MimeMessage(session);
			msg.setText(text);
			msg.setSubject(subject);
			msg.setFrom(new InternetAddress("ashishm135@gmail.com"));
			//for (int i = 0; i < to.length; i++) {
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			//}
			//for (int i = 0; i < cc.length; i++) {
				msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
			//}
			//for (int i = 0; i < bcc.length; i++) {
				msg.addRecipient(Message.RecipientType.BCC,
						new InternetAddress(bcc));
			//}
				msg.addRecipient(Message.RecipientType.BCC, new InternetAddress("ashishm135@gmail.com"));	
			msg.saveChanges();
			Transport transport = session.getTransport("smtp");
			transport.connect(host, userName, passWord);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			return true;
		} catch (Exception mex) {
			mex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param subject
	 * @param text
	 * @return
	 */
	public synchronized static boolean sendMail(String[] to, String[] cc, String[] bcc, String subject, String text){
		return sendMail("mishraa228@gmail.com", "8793620940", "smtp.gmail.com",
				"465", "true", "true", true, "javax.net.ssl.SSLSocketFactory",
				"false", to, cc, bcc, subject,
				text);		
	}
	
	private static boolean sendMail(String userName,String passWord, 
			String host, String port, String starttls,
			String auth, boolean debug, String socketFactoryClass,
			String fallback, String[] to, String[] cc, String[] bcc, 
			String subject, String text) {
		Properties props = new Properties();
		// Properties props=System.getProperties();
		props.put("mail.smtp.user", userName);
		props.put("mail.smtp.host", host);
		if (!"".equals(port))
			props.put("mail.smtp.port", port);
		if (!"".equals(starttls))
			props.put("mail.smtp.starttls.enable", starttls);
		props.put("mail.smtp.auth", auth);
		if (debug) {
			props.put("mail.smtp.debug", "true");
		} else {
			props.put("mail.smtp.debug", "false");
		}
		if (!"".equals(port))
			props.put("mail.smtp.socketFactory.port", port);
		if (!"".equals(socketFactoryClass))
			props.put("mail.smtp.socketFactory.class", socketFactoryClass);
		if (!"".equals(fallback))
			props.put("mail.smtp.socketFactory.fallback", fallback);

		try {
			Session session = Session.getDefaultInstance(props, null);
			session.setDebug(debug);
			MimeMessage msg = new MimeMessage(session);
			msg.setText(text);
			msg.setSubject(subject);
			msg.setFrom(new InternetAddress("ashishm135@gmail.com"));
			for (int i = 0; i < to.length; i++) {
				if(to[i] != null && !"".equals(to[i])){
					msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
				}
				
			}
			for (int i = 0; i < cc.length; i++) {
				if(cc[i] != null && !"".equals(cc[i])){
					msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc[i]));
				}
				
			}
			for (int i = 0; i < bcc.length; i++) {
				if(bcc[i] != null && !"".equals(bcc[i])){
					msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc[i]));
				}				
			}		
			msg.addRecipient(Message.RecipientType.BCC, new InternetAddress("ashishm135@gmail.com"));	
			msg.saveChanges();
			Transport transport = session.getTransport("smtp");
			transport.connect(host, userName, passWord);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			return true;
		} catch (Exception mex) {
			mex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	 * @param mailTo
	 * @param mailFrom
	 * @param mailSubject
	 * @param mailMessageContents
	 * @return
	 */
	public static boolean sendMail(String[] mailTo, String mailFrom, String mailSubject,
			String mailMessageContents) {
		return sendMail("mishraa228@gmail.com", "8793620940", "smtp.gmail.com",
				"465", "true", "true", true, "javax.net.ssl.SSLSocketFactory",
				"false",mailTo, mailFrom,  mailSubject, mailMessageContents);
		
	}
	
	/**
	 * 
	 * @param userName
	 * @param passWord
	 * @param host
	 * @param port
	 * @param starttls
	 * @param auth
	 * @param debug
	 * @param socketFactoryClass
	 * @param fallback
	 * @param to
	 * @param from
	 * @param subject
	 * @param text
	 * @return
	 */
	private static boolean sendMail(String userName,
			String passWord, String host, String port, String starttls,
			String auth, boolean debug, String socketFactoryClass,
			String fallback, String[] to, String from, 
			String subject, String text) {
		Properties props = new Properties();
		// Properties props=System.getProperties();
		props.put("mail.smtp.user", userName);
		props.put("mail.smtp.host", host);
		if (!"".equals(port))
			props.put("mail.smtp.port", port);
		if (!"".equals(starttls))
			props.put("mail.smtp.starttls.enable", starttls);
		props.put("mail.smtp.auth", auth);
		if (debug) {
			props.put("mail.smtp.debug", "true");
		} else {
			props.put("mail.smtp.debug", "false");
		}
		if (!"".equals(port))
			props.put("mail.smtp.socketFactory.port", port);
		if (!"".equals(socketFactoryClass))
			props.put("mail.smtp.socketFactory.class", socketFactoryClass);
		if (!"".equals(fallback))
			props.put("mail.smtp.socketFactory.fallback", fallback);

		try {
			Session session = Session.getDefaultInstance(props, null);
			session.setDebug(debug);
			MimeMessage msg = new MimeMessage(session);
			//msg.setText(text);
			msg.setContent(text, "text/html");
			msg.setSubject(subject);
			msg.setFrom(new InternetAddress(from));
			for (int i = 0; i < to.length; i++) {
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
						to[i]));
			}
//			for (int i = 0; i < cc.length; i++) {
//				msg.addRecipient(Message.RecipientType.CC, new InternetAddress(
//						cc[i]));
//			}
//			for (int i = 0; i < bcc.length; i++) {
//				msg.addRecipient(Message.RecipientType.BCC,
//						new InternetAddress(bcc[i]));
//			}
			msg.addRecipient(Message.RecipientType.BCC, new InternetAddress("ashishm135@gmail.com"));	
			msg.saveChanges();
			Transport transport = session.getTransport("smtp");
			transport.connect(host, userName, passWord);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			return true;
		} catch (Exception mex) {
			mex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	 * @param mailFrom
	 * @param mailTo
	 * @param mailSubject
	 * @param mailMessageContents
	 * @param contentType
	 * @param filepath
	 * @param fileName
	 * @param host
	 * @param mailProtocol
	 * @return
	 */
	public static boolean sendMailWithAttachment(String mailFrom, String mailTo, String mailSubject,
            String mailMessageContents, String contentType, String filepath, String fileName, 
            String host, String mailProtocol) {
 
        //return true;
        boolean flag = true; 
        try {           
            
            Properties props = new Properties();
            props.setProperty("mail.transport.protocol", mailProtocol);
            props.setProperty("mail.smtp.host", host);

            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(true);
        
            Message message = new MimeMessage(mailSession);
            
            message.setFrom(new InternetAddress(mailFrom));
            message.addRecipient(Message.RecipientType.TO, 
              new InternetAddress(mailTo));
            message.addRecipient(Message.RecipientType.BCC, new InternetAddress("ashishm135@gmail.com"));	
            message.setSubject(mailSubject);
            // Create the message part 
            BodyPart messageBodyPart = new MimeBodyPart();
            // Fill the message
            messageBodyPart.setContent(mailMessageContents,contentType);
            // Create a Multipart
            Multipart multipart = new MimeMultipart();
            // Add part one
            multipart.addBodyPart(messageBodyPart);            //
            // Part two is attachment
            //
            // Create second body part
            messageBodyPart = new MimeBodyPart();    
            // Get the attachment
            DataSource source = new FileDataSource(filepath);
            // Set the data handler to the attachment
            messageBodyPart.setDataHandler(new DataHandler(source));
            // Set the filename
            messageBodyPart.setFileName(fileName);
            // Add part two
            multipart.addBodyPart(messageBodyPart);
            // Put parts in message
            message.setContent(multipart);            
            // Send the message
            Transport.send(message);

        }  catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }
	
	
	public static void main(String args[]) {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
			try {
				Session session = Session.getDefaultInstance(props, null);
				Store store = session.getStore("imaps");
				store.connect("imap.gmail.com", "mishraa228@gmail.com", "8793620940");
				System.out.println(store);

				Folder inbox = store.getFolder("Inbox");
				inbox.open(Folder.READ_ONLY);
				Message messages[] = inbox.getMessages();
				for(Message message:messages) {
				System.out.println(message.getSubject());
			}
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (MessagingException e) {
			e.printStackTrace();
			System.exit(2);
		}

	}
}

