package br.com.openliber.model;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class EmailMensagem {
	private String titulo;

	@Transient
	private String tokenEmail;
	private String mensagem;
	private String controller;

	public EmailMensagem() {

	}

	public EmailMensagem(String titulo, String tokenEmail, String mensagem) {
		super();
		this.titulo = titulo;
		this.tokenEmail = tokenEmail;
		this.mensagem = mensagem;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTokenEmail() {
		return tokenEmail;
	}

	public void setTokenEmail(String tokenEmail) {
		this.tokenEmail = tokenEmail;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

	public String gerarMensagem() {
		StringBuilder mensagem = new StringBuilder();

		// Gerando titulo da mensagem
		mensagem.append("<h1>" + this.getTitulo() + "</h1><br><br>");

		// Gerando corpo da mensagem
		mensagem.append("<p>" + this.getMensagem() + "</p><br>");

		// Gerando link token
		mensagem.append("<p>" + "<a href=\"localhost:8090" + this.getController() + "?token=" + this.getTokenEmail()
				+ "\">" + "localhost:8090" + this.getController() + "?token=" + this.getTokenEmail() + "</a>");

		return mensagem.toString();
	}
}
