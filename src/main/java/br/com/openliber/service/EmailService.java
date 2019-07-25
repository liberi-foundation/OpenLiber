package br.com.openliber.service;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.openliber.DAO.EmailDAO;
import br.com.openliber.model.Email;

@Service
public class EmailService {
	private final String username = "liberifoundation@gmail.com";
	private final String password = "liberi123";

	@Autowired
	private EmailDAO emailRep;

	public void salvarRegistro(Email email) {
		this.emailRep.save(email);
	}

	public void sendEmailTSL(Email email) throws MessagingException {
		// Gerando token do email
		email.setToken(UUID.randomUUID().toString());

		// Setando token na mensagem
		email.getMensagem().setTokenEmail(email.getToken());

		// Propriedades do transport
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
		message.setFrom(new InternetAddress(email.getEmailRemetente()));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getEmailDestinatario()));
		message.setSubject(email.getAssunto());
		message.setContent(email.getMensagem().gerarMensagem(), "text/html");

		Transport.send(message);
		this.emailRep.save(email);
	}
}
