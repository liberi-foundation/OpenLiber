package br.com.openliber.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.openliber.DAO.TransacaoDAO;
import br.com.openliber.model.pagseguro.Transacao;

public class TransacaoService {

	@Autowired
	private TransacaoDAO transacaoRep;

	private void save(Transacao transacao) {
		this.transacaoRep.save(transacao);
	}

	public void salvar(Transacao transacao) {

		this.save(transacao);
	}
}
