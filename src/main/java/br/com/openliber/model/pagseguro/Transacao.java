package br.com.openliber.model.pagseguro;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.com.openliber.enums.StatusTransacaoEnum;

@Entity
public class Transacao {
	@Column(name = "id_transacao")
	private Integer id;
	private Integer id_pagseguro;
	private LocalDate data;
	private StatusTransacaoEnum status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_pagseguro() {
		return id_pagseguro;
	}

	public void setId_pagseguro(Integer id_pagseguro) {
		this.id_pagseguro = id_pagseguro;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public StatusTransacaoEnum getStatus() {
		return status;
	}

	public void setStatus(StatusTransacaoEnum status) {
		this.status = status;
	}
}
