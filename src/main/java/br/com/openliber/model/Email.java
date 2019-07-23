package br.com.openliber.model;

import java.time.LocalDate;

import javax.persistence.Entity;

@Entity
public class Email {
	private String nomeRemetente;
	private String emailRemetente;
	private String nomeDestiantario;
	private String emailDestinatario;
	private String assunto;
	private String mensagem;
	private String token;
	private LocalDate validade;

	public Email() {

	}

	public String getNomeRemetente() {
		return nomeRemetente;
	}

	public void setNomeRemetente(String nomeRemetente) {
		this.nomeRemetente = nomeRemetente;
	}

	public String getEmailRemetente() {
		return emailRemetente;
	}

	public void setEmailRemetente(String emailRemetente) {
		this.emailRemetente = emailRemetente;
	}

	public String getNomeDestiantario() {
		return nomeDestiantario;
	}

	public void setNomeDestiantario(String nomeDestiantario) {
		this.nomeDestiantario = nomeDestiantario;
	}

	public String getEmailDestinatario() {
		return emailDestinatario;
	}

	public void setEmailDestinatario(String emailDestinatario) {
		this.emailDestinatario = emailDestinatario;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDate getValidade() {
		return validade;
	}

	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}
}
