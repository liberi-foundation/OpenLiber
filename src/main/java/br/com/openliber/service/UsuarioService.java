package br.com.openliber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.openliber.DAO.UsuarioDAO;
import br.com.openliber.model.Usuario;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	public Usuario findUsuarioByEmail(String email) {
		return usuarioDAO.findByEmail(email);
	}
	

	public void criarUsuario(Usuario usuario) throws Exception {
		if(this.findUsuarioByEmail(usuario.getEmail()) != null){
			
		}
	}
	
	
	
}
