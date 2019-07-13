package br.com.openliber.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.openliber.model.Livro;

public interface LivroDAO extends JpaRepository<Livro, Integer> {

}
