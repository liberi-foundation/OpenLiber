package br.com.openliber.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.openliber.DAO.UsuarioDAO;
import br.com.openliber.enums.TipoUsuarioEnum;
import br.com.openliber.exception.ServiceException;
import br.com.openliber.exception.StorageException;
import br.com.openliber.model.Email;
import br.com.openliber.model.EmailMensagem;
import br.com.openliber.model.Nacionalidade;
import br.com.openliber.model.Storage;
import br.com.openliber.model.Usuario;

@Service
public class UsuarioService {

	private EmailService emailService = new EmailService();

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
		usuario.setToken(UUID.randomUUID().toString());

		/*
		 * Envio do email
		 */
		// Setando validade
		LocalDate validade = LocalDate.now();
		validade.plusDays(5);

		// Criando mensagem do email
		EmailMensagem mensagem = new EmailMensagem();
		mensagem.setTitulo("Olá, " + usuario.getNome() + ". Ative sua conta!");
		mensagem.setMensagem("Para ter acesso total ao nosso site ative sua conta. (este email vence em: "
				+ validade.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ".");
		;
		mensagem.setController("/ativarConta");

		// Configurando email
		Email emailConfirmacao = new Email();
		emailConfirmacao.setAssunto("Openliber - confirmar conta!");
		emailConfirmacao.setMensagem(mensagem);
		emailConfirmacao.setNomeRemetente("Openliber");
		emailConfirmacao.setEmailRemetente("openliber@gmail.com");
		emailConfirmacao.setNomeDestiantario(usuario.getNome() + " " + usuario.getSobrenome());
		emailConfirmacao.setEmailDestinatario(usuario.getEmail());
		emailConfirmacao.setValidade(validade);

		try {
			this.emailService.sendEmailTSL(emailConfirmacao);
			this.emailService.salvarRegistro(emailConfirmacao);
		} catch (MessagingException e) {
		}

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
