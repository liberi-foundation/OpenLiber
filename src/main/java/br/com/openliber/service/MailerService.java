package br.com.openliber.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.openliber.model.Mailer;

public class MailerService {
	private final String username = "liberifoundation@gmail.com";
	private final String password = "liberi123";

	public void sendEmailTSL(Mailer mailer) throws MessagingException {
		// Propriedades do email
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		session.setDebug(true);

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(mailer.getRemetente().getEmail()));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailer.getDestinatario().getEmail()));
		message.setSubject(mailer.getAssunto());
		message.setText(mailer.getMensagem());
		Transport.send(message);
	}
}
