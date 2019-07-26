package br.com.openliber.service;

import java.util.UUID;

import javax.mail.MessagingException;

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
	private EmailService emailService;

	@Autowired
	private UsuarioDAO usuarioDAO;

	public Usuario findUsuarioByEmail(String email) {
		return usuarioDAO.findByEmailIgnoreCase(email);
	}

	public Usuario findByEmail(String email) {
		return usuarioDAO.findByEmailIgnoreCase(email);
	}

	public Usuario findById(Integer id) {
		return this.usuarioDAO.findByID(id);
	}

	public Usuario findByApelido(String apelido) {
		return this.usuarioDAO.findByApelidoIgnoreCase(apelido);
	}

	public boolean save(Usuario usuario) {
		if (this.usuarioDAO.save(usuario) != null) {
			return true;
		}

		return false;
	}

	public void criarUsuario(Usuario usuario) throws ServiceException {
		if (this.findUsuarioByEmail(usuario.getEmail()) != null) {
			throw new ServiceException("Já existe um usuário com este e-mail: " + usuario.getEmail());
		}

		if (this.findByApelido(usuario.getApelido()) != null) {
			throw new ServiceException("Já existe um usuário com este apelido: " + usuario.getApelido());
		}

		usuario.setTipoUsuario(TipoUsuarioEnum.PADRAO);
		usuario.setToken(UUID.randomUUID().toString());

		if (this.save(usuario)) {
			try {
				this.emailService.enviarConfirmacaoDeConta(usuario);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
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
		
		if (!(usuarioOriginal.getApelido().equals(usuario.getApelido()))) {
			if (this.findByApelido(usuario.getApelido()) != null) {
				throw new ServiceException("Já existe um usuário com este apelido: " + usuario.getApelido());
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

		if (usuario.getAtivo() == false) {
			throw new ServiceException("Conta desativada");
		}

		return usuario;
	}
}
