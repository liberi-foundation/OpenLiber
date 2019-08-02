package br.com.openliber.model.pagseguro;

public class Pagseguro {
	private final String email = "joaovitorvinezof@gmail.com";
	private final String token = "3445221D340743E0B47B7A93DE8CA1B6";
	private final String credenciais = "email=" + this.email + "&token=" + this.token;

	private String servico;
	private String endpoint;
	private Checkout checkout;

	public Pagseguro() {

	}

	public Pagseguro(Checkout checkout) {
		super();
		this.checkout = checkout;
	}

	public Pagseguro(String servico) {
		super();
		this.servico = servico;
	}

	public Pagseguro(String servico, String endpoint, Checkout checkout) {
		super();
		this.servico = servico;
		this.endpoint = endpoint;
		this.checkout = checkout;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public Checkout getCheckout() {
		return checkout;
	}

	public void setCheckout(Checkout checkout) {
		this.checkout = checkout;
	}

	public String getEmail() {
		return email;
	}

	public String getToken() {
		return token;
	}

	public String getCredenciais() {
		return credenciais;
	}
}
