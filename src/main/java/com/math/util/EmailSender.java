package com.math.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
	public void sendMail(String email, String subject, String letter) {
		final String username = "simplemathautosender";
        final String password = "j93hnzlr5f0bmxym";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("simplemathautosender@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(email));
            message.setSubject(subject);
            message.setText(letter);

            Transport.send(message);

            System.out.println(this.getClass() + " Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
	}
}
