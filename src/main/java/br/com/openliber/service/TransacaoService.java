package br.com.openliber.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.openliber.DAO.TransacaoDAO;
import br.com.openliber.model.pagseguro.Transacao;

@Service
public class TransacaoService {

	@Autowired
	private TransacaoDAO transacaoRep;

	private void save(Transacao transacao) {
		this.transacaoRep.save(transacao);
	}

	public void salvar(Transacao transacao) {
		this.save(transacao);
	}

	public void retorno(String idPagseguro) {
		Transacao transacao = new Transacao();
		transacao.setIdPagseguro(idPagseguro);
		transacao.setData(LocalDate.now());

		this.salvar(transacao);
	}
}
