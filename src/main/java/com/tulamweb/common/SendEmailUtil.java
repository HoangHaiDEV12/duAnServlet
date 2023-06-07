package com.tulamweb.common;

import java.util.Date;


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

public class SendEmailUtil {

	public static void sendEmail(String host, String port, final String userName, final String password,
			String toAddress, String subject, String message) throws AddressException, MessagingException {
		Properties pro = new Properties();
		pro.put("mail.smtp.host", "smtp.gmail.com");
		pro.put("mail.smtp.starttls.enable", "true");
		pro.put("mail.smtp.auth", "true");
		pro.put("mail.smtp.port", "587");

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};

		Session session = Session.getInstance(pro, auth);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(userName));
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };

		try {
			msg.setRecipients(Message.RecipientType.TO, toAddresses);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setText(message);

			// sends the e-mail
			Transport.send(msg);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
