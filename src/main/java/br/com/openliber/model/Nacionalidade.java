package br.com.openliber.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Nacionalidade {

	@Column(length = 80)
	private String cidade;
	@Column(length = 80)
	private String estado;
	@Column(length = 80)
	private String pais;

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

}
