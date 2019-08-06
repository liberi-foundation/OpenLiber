package br.com.openliber.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.openliber.model.LivroAcesso;

public interface LivroAcessoDAO extends JpaRepository<LivroAcesso, Integer> {

}
