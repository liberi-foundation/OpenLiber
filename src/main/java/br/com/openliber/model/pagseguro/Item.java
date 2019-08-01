package br.com.openliber.model.pagseguro;

import javax.persistence.Embeddable;

@Embeddable
public class Item {
	private int id;
	private String descricao;
	private double valor;
	private int quantidade = 1;

	public Item() {

	}

	public Item(int id, String descricao, double valor, int quantidade) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.quantidade = quantidade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
