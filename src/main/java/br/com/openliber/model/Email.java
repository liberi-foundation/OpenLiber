package br.com.openliber.model;

public class Email {
	private String nome;
	private String email;

	public Email() {
	}

	public Email(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Email [nome=" + nome + ", email=" + email + "]";
	}
}
