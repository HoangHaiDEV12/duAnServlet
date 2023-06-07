package com.tulamweb;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Test {
	public static void main(String[] args) {
		// Send email
		sendEmail();
		
	}
	
	/**
	 * Send the email via SMTP using TLS and SSL
	 */
	private static void sendEmail() {
 
		// Create all the needed properties
		Properties connectionProperties = new Properties();
		// SMTP host
		connectionProperties.put("mail.smtp.host", "smtp.gmail.com");
		// Is authentication enabled
		connectionProperties.put("mail.smtp.auth", "true");
		// Is TLS enabled
		connectionProperties.put("mail.smtp.starttls.enable", "true");
		// SMTP port, the same as SSL port :)
		connectionProperties.put("mail.smtp.port", "587");
		
		System.out.print("Creating the session...");
		
		// Create the session
		Session session = Session.getDefaultInstance(connectionProperties,
				new javax.mail.Authenticator() {	// Define the authenticator
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("dotronghai123456789@gmail.com","azogzujhdnhhftxg");
					}
				});
		
		System.out.println("done!");
		
		// Create and send the message
		try {
			// Create the message 
			Message message = new MimeMessage(session);
			// Set sender
			message.setFrom(new InternetAddress("dotronghai123456789@gmail.com"));
			// Set the recipients
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("hoanghaimazda@gmail.com"));
			// Set message subject
			message.setSubject("Hello from Team ITCuties");
			// Set message text
			message.setText("Java is easy when you watch our tutorials ;)");
			
			System.out.print("Sending message...");
			// Send the message
			Transport.send(message);
			
			System.out.println("done!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
