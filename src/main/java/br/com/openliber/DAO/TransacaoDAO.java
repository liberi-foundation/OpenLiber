package br.com.openliber.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.openliber.model.pagseguro.Transacao;

public interface TransacaoDAO extends JpaRepository<Transacao, Integer> {

}
