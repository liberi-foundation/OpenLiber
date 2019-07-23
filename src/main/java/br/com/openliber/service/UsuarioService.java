package br.com.openliber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.openliber.DAO.UsuarioDAO;
import br.com.openliber.enums.TipoUsuarioEnum;
import br.com.openliber.exception.ServiceException;
import br.com.openliber.exception.StorageException;
import br.com.openliber.model.Nacionalidade;
import br.com.openliber.model.Storage;
import br.com.openliber.model.Usuario;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;

	public Usuario findUsuarioByEmail(String email) {
		return usuarioDAO.findByEmail(email);
	}

	public Usuario findById(Integer id) {
		return this.usuarioDAO.findByID(id);
	}

	public void criarUsuario(Usuario usuario) throws ServiceException {
		if (this.findUsuarioByEmail(usuario.getEmail()) != null) {
			throw new ServiceException("Já existe um usuário com este e-mail: " + usuario.getEmail());
		}

		usuario.setTipoUsuario(TipoUsuarioEnum.PADRAO);

		this.usuarioDAO.save(usuario);
	}

	public void atualizarUsuario(Usuario usuario) throws ServiceException, StorageException {
		Usuario usuarioOriginal = this.findById(usuario.getId());

		if (usuario.getNacionalidade() == null) {
			usuario.setNacionalidade(new Nacionalidade("", "", ""));
		}

		if (!(usuarioOriginal.getEmail().equals(usuario.getEmail()))) {
			if (this.findUsuarioByEmail(usuario.getEmail()) != null) {
				throw new ServiceException("Já existe um usuário com este e-mail: " + usuario.getEmail());
			}
		}

		if (usuario.getConfirmarSenha() == "") {
			usuario.setSenha(usuarioOriginal.getSenha());
		}

		if (usuario.getFotoTemp() != null) {
			Storage storage = new Storage(usuario);

			usuario.setFoto(storage.savarImagem(usuario.getFotoTemp()));
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
