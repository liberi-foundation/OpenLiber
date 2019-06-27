package br.com.openliber.model;

public class Mailer {
	private Email remetente;
	private Email destinatario;
	private String assunto;
	private String mensagem;

	public Mailer() {}
	
	public Mailer (Email remetente, Email destinatario) {
		this.remetente = remetente;
		this.destinatario = destinatario;
	}
	
	public Email getRemetente() {
		return remetente;
	}

	public void setRemetente(Email remetente) {
		this.remetente = remetente;
	}

	public Email getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Email destinatario) {
		this.destinatario = destinatario;
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

	@Override
	public String toString() {
		return "Mailer [remetente=" + remetente + ", destinatario=" + destinatario + ", assunto=" + assunto
				+ ", mensagem=" + mensagem + "]";
	}
}
