package br.com.openliber.service;

import java.util.List;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.openliber.DAO.LivroAcessoDAO;
import br.com.openliber.DAO.LivroDAO;
import br.com.openliber.DAO.UsuarioDAO;
import br.com.openliber.exception.ServiceException;
import br.com.openliber.exception.StorageException;
import br.com.openliber.model.Livro;
import br.com.openliber.model.LivroAcesso;
import br.com.openliber.model.Storage;
import br.com.openliber.model.Usuario;

@Service
public class LivroAcessoService {
	
	@Autowired
	private LivroAcessoDAO livroAcessoRep;
	
	public LivroAcesso findLogLeituraDeUmLivroByIdUsuarioAndIdLivro(Integer idUsuario, Integer idLivro ) {
		
		return livroAcessoRep.findLogLeituraDeUmLivroByIdUsuarioAndIdLivro(idUsuario, idLivro);
	}
	
	
	public List<LivroAcesso> findLogTodosLivrosQueUsuarioLeuByIdUsuario(Integer idUsuario ){
		return livroAcessoRep.findLogTodosLivrosQueUsuarioLeuByIdUsuario(idUsuario);
	}
	
	public List<LivroAcesso> findLogTodosUsuariosQueLeramLivroByIdLivro(Integer idLivro ){
		return livroAcessoRep.findLogTodosUsuariosQueLeramLivroByIdLivro(idLivro);
	}

	public LivroAcesso findLogById(Integer idLog ){
		return livroAcessoRep.findLogById(idLog);
		
	}
	
	public void save(LivroAcesso livroAcesso) {
		livroAcessoRep.save(livroAcesso);
	}

	

}
