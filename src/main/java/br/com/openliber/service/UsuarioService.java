package br.com.openliber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.openliber.DAO.UsuarioDAO;
import br.com.openliber.exception.ServiceException;
import br.com.openliber.model.Usuario;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;

	public Usuario findUsuarioByEmail(String email) {
		return usuarioDAO.findByEmail(email);
	}

	public void criarUsuario(Usuario usuario) throws Exception {
		if (this.findUsuarioByEmail(usuario.getEmail()) != null) {
			throw new ServiceException("Já existe um usuário com este e-mail:" + usuario.getEmail());
		}

		this.usuarioDAO.save(usuario);
	}

	public Usuario efetuarLogin(String email, String senha) throws ServiceException {
		Usuario usuario = this.usuarioDAO.efetuarLogin(email, senha);
		
		if (usuario == null) {
			throw new ServiceException("Login/senha não encontrados");
		}
		
		return usuario; 
	}
}
