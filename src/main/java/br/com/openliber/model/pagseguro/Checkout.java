package br.com.openliber.model.pagseguro;

public class Checkout {
	private String currency = "BRL";
	private int timeout = 25;
	private Item item;

	public Checkout() {

	}

	public Checkout(Item item) {
		super();
		this.item = item;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
}
