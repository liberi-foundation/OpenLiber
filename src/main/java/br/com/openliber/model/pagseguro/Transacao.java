package br.com.openliber.model.pagseguro;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.openliber.enums.StatusTransacaoEnum;

@Entity
public class Transacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_transacao")
	private Integer id;
	
	
	private String idPagseguro;
	private LocalDate data;
	private StatusTransacaoEnum status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdPagseguro() {
		return idPagseguro;
	}

	public void setIdPagseguro(String idPagseguro) {
		this.idPagseguro = idPagseguro;
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
