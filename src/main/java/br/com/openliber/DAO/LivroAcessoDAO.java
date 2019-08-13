package br.com.openliber.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


import br.com.openliber.model.LivroAcesso;

public interface LivroAcessoDAO extends JpaRepository<LivroAcesso, Integer> {

	
	@Query("FROM LivroAcesso la WHERE la.usuario.id = :idUsuario AND la.livro.id = :idLivro")
	public LivroAcesso findLogLeituraDeUmLivroByIdUsuarioAndIdLivro(Integer idUsuario, Integer idLivro );

	
	
	@Query("FROM LivroAcesso la WHERE la.usuario.id = :idUsuario")
	public List<LivroAcesso> findLogTodosLivrosQueUsuarioLeuByIdUsuario(Integer idUsuario );
	
	
	@Query("FROM LivroAcesso la WHERE la.livro.id = :idLivros")
	public List<LivroAcesso> findLogTodosUsuariosQueLeramLivroByIdLivro(Integer idLivro );

	@Query("FROM LivroAcesso la WHERE la.idLog = :idLog")
	public LivroAcesso findLogById(Integer idLog );

	
	

}
