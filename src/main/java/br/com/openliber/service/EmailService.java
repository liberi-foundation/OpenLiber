package br.com.openliber.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import br.com.openliber.model.EmailMensagem;
import br.com.openliber.model.Usuario;

@Service
public class EmailService {
	private final String username = "liberifoundation@gmail.com";
	private final String password = "liberi123";

	@Autowired
	private EmailDAO emailRep;

	public Email findByToken(String token) {
		return this.emailRep.findByToken(token);
	}

	public void salvarRegistro(Email email) {
		this.emailRep.save(email);
	}

	public boolean validarVencimento(Email email) {
		LocalDate agora = LocalDate.now();

		if (email.getValidade().isAfter(agora)) {
			return true;
		}

		return false;
	}
	
	public void enviarConfirmacaoDeConta(Usuario usuario) throws MessagingException {
		/*
		 * Envio do email
		 */
		// Setando validade
		LocalDate agora = LocalDate.now();
		LocalDate validade = agora.plusDays(5);

		// Criando mensagem do email
		EmailMensagem mensagem = new EmailMensagem();
		mensagem.setTitulo("Olá, " + usuario.getApelido() + ". Ative sua conta!");
		mensagem.setMensagem("Para ter acesso total ao nosso site ative sua conta. (este email vence em: "
				+ validade.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")");
		;
		mensagem.setController("/ativarConta");

		// Configurando email
		Email emailConfirmacao = new Email();
		emailConfirmacao.setAssunto("Openliber - confirmar conta!");
		emailConfirmacao.setMensagem(mensagem);
		emailConfirmacao.setNomeRemetente("Openliber");
		emailConfirmacao.setEmailRemetente("openliber@gmail.com");
		emailConfirmacao.setNomeDestiantario(usuario.getApelido());
		emailConfirmacao.setEmailDestinatario(usuario.getEmail());
		emailConfirmacao.setValidade(validade);
		
		this.sendEmailTSL(emailConfirmacao);
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