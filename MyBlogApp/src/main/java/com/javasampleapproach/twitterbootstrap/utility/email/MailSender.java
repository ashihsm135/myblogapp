package com.javasampleapproach.twitterbootstrap.utility.email;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
 
@Component("javasampleapproachMailSender")
public class MailSender {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void sendMail(String from, String to, String subject, String body) {
		
		SimpleMailMessage mail = new SimpleMailMessage();
 
		mail.setFrom(from);
		mail.setTo(to);
		mail.setSubject(subject);
		mail.setText(body);
		
		logger.info("Sending...");
		
		javaMailSender.send(mail);
		
		logger.info("Done!");
	}
	
	
	public static void main(String [] args){
		
		MailSender mailSender = new MailSender();
		String from = "ashishm135@gmail.com";
		String to = "ashishm135@gmail.com";
		String subject = "JavaMailSender";
		String body = "Just-Testing!";
		
		mailSender.sendMail(from, to, subject, body);
		
	}
}