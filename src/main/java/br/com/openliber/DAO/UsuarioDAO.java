package br.com.openliber.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.openliber.model.Usuario;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {
	public Usuario findByEmailIgnoreCase(String email);

	@Query("SELECT u FROM Usuario u WHERE u.nome LIKE %:nome% OR u.email LIKE %:email%")
	public Usuario findByNomeEmailAprox(String nome, String email);

	@Query("SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha")
	public Usuario efetuarLogin(String email, String senha);

	@Query("FROM Usuario u WHERE u.id = :id")
	public Usuario findByID(Integer id);

	public Usuario findByApelidoIgnoreCase(String apelido);
}
