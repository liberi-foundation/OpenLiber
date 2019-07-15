package br.com.openliber.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.openliber.model.Livro;

public interface LivroDAO extends JpaRepository<Livro, Integer> {
	
	@Query("FROM Livro l WHERE l.autor.email = :emailOfAutor AND l.titulo = :titulo")
	public Livro findByEmailOfAutorAndTitulo(String emailOfAutor, String titulo);
}
