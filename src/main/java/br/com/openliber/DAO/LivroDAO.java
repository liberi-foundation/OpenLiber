package br.com.openliber.DAO;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.openliber.model.Livro;

public interface LivroDAO extends JpaRepository<Livro, Integer> {
	@Query("FROM Livro l WHERE l.id = :id")
	public Livro findByID(Integer id);

	@Query("FROM Livro l WHERE l.autor.email = :emailOfAutor AND l.titulo = :titulo")
	public Livro findByEmailOfAutorAndTitulo(String emailOfAutor, String titulo);

	@Query("FROM Livro l WHERE l.autor.email = :emailOfAutor ORDER BY l.id DESC")
	public List<Livro> findByEmailOfAutor(String emailOfAutor);

	@Query("FROM Livro l WHERE l.autor.email = :emailOfAutor ORDER BY l.id DESC")
	public List<Livro> findByEmailOfAutorLasts(String emailOfAutor);

	public List<Livro> findByTituloContainingIgnoreCase(String titulo);

	@Query("FROM Livro l WHERE lower(l.autor.apelido) = :apelido AND lower(l.titulo) = :titulo")
	public Livro findByAutorApelidoAndTitulo(String apelido, String titulo);
}
