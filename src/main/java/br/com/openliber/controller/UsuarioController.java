package br.com.openliber.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.openliber.exception.ServiceException;
import br.com.openliber.exception.StorageException;
import br.com.openliber.model.Email;
import br.com.openliber.model.Nacionalidade;
import br.com.openliber.model.Usuario;
import br.com.openliber.service.EmailService;
import br.com.openliber.service.LivroService;
import br.com.openliber.service.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private LivroService livroService;

	@Autowired
	private EmailService emailService;

	/*
	 * Cadastro
	 */
	@GetMapping("/cadastro")
	public ModelAndView exibirTelaCadastro() {
		ModelAndView mv = new ModelAndView("/cadastro");
		mv.addObject("usuario", new Usuario());
		return mv;
	}

	@PostMapping("/cadastro")
	public String salvarUsuario(@Valid @ModelAttribute Usuario usuario, Errors errors, RedirectAttributes ra) {
		if (errors.hasErrors()) {
			ra.addFlashAttribute("mensagemErro", "Não foi possível criar usuário: " + errors.getFieldErrors());

			return "redirect:/cadastro";
		} else {
			try {
				this.usuarioService.criarUsuario(usuario);
				ra.addFlashAttribute("mensagem", "Conta criada com sucesso!");
			} catch (ServiceException | MessagingException e) {
				ra.addFlashAttribute("mensagemErro", "Não foi possível criar usuário: " + e.getMessage());

				return "redirect:/cadastro";
			}
		}

		ra.addFlashAttribute("contaCriada", true);
		return "redirect:/cadastro/ativar";
	}

	/*
	 * Login / Logout
	 */
	@GetMapping("/login")
	public ModelAndView login(HttpServletRequest request, RedirectAttributes ra) {
		ModelAndView mv = new ModelAndView("/login");
		mv.addObject("usuario", new Usuario());

		if (request.getAttribute("acessoNegado") != null) {
			boolean acessoNegado = (boolean) request.getAttribute("acessoNegado");
			String retorno = (String) request.getAttribute("retorno");
			if (acessoNegado == true) {
				mv.addObject("acessoNegado", request.getRequestURI());
				mv.addObject("retorno", retorno);
			}
		}

		return mv;
	}

	@PostMapping("/login")
	public String efetuarLogin(HttpServletRequest request, @ModelAttribute Usuario usuario,
			@RequestParam(name = "retorno", required = false) String retorno, RedirectAttributes ra,
			HttpSession session) {
		String redirect = "redirect:/inicio";
		if (retorno != null) {
			redirect = "redirect:" + retorno;
		}

		Usuario usuarioLogado;
		try {
			usuarioLogado = this.usuarioService.efetuarLogin(usuario.getEmail(), usuario.getSenha());
			session.setAttribute("usuarioLogado", usuarioLogado);
		} catch (ServiceException e) {
			ra.addFlashAttribute("mensagemErro", e.getMessage());

			return "redirect:/login";
		}

		ra.addFlashAttribute("loginEfetuado", true);
		return redirect;
	}

	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();

		return "redirect:/inicio";
	}

	/*
	 * Perfil
	 */
	@GetMapping("/perfil/{owner}")
	public ModelAndView exibirPerfil(@PathVariable String owner, RedirectAttributes ra) {
		ModelAndView mv = new ModelAndView("/perfil");

		Usuario usuario = this.usuarioService.findUsuarioByEmail(owner);
		if (usuario == null) {
			ra.addFlashAttribute("alertErro", "Perfil não encontrado");

			mv.setViewName("redirect:/inicio");
			return mv;
		}

		if (usuario.getNacionalidade() == null) {
			usuario.setNacionalidade(new Nacionalidade("?", "?", "?"));
		}

		int livrosPostados = this.livroService.findByEmailOfAutor(usuario.getEmail()).size();

		mv.addObject("usuario", usuario);
		mv.addObject("livrosPostados", livrosPostados);

		if (livrosPostados > 0) {
			mv.addObject("livros", this.livroService.findLastsByAutor(usuario, 4));
		}

		return mv;
	}

	@GetMapping("/perfil/editar")
	public ModelAndView exibirEditarPerfil(HttpSession session, RedirectAttributes ra) {
		ModelAndView mv = new ModelAndView("/perfil-editar");

		if (session.getAttribute("usuarioLogado") == null) {
			ra.addFlashAttribute("acessoNegado", true);
			ra.addFlashAttribute("retorno", "/perfil/editar");

			mv.setViewName("redirect:/login");
			return mv;
		}

		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

		mv.addObject(usuario);

		return mv;
	}

	@PostMapping("/perfil/editar")
	public String editarPerfil(@RequestParam(name = "fotoArquivo", required = false) MultipartFile fotoArquivo,
			@Valid @ModelAttribute Usuario usuario, BindingResult br, HttpSession session, RedirectAttributes ra) {
		usuario.setFotoTemp(fotoArquivo);

		if (br.hasErrors()) {
			ra.addFlashAttribute("erros", br.getAllErrors());

			return "redirect:/perfil/editar";
		}

		try {
			this.usuarioService.atualizarUsuario(usuario);
			session.setAttribute("usuarioLogado", usuario);

			return "redirect:/perfil/" + usuario.getEmail();
		} catch (ServiceException | StorageException e) {
			ra.addFlashAttribute("erro", e.getMessage());

			return "redirect:/perfil/editar";
		}
	}

	/*
	 * Ativar conta
	 */
	// mensagem que o email foi enviado
	@GetMapping("/cadastro/ativar")
	public String ativeSuaConta() {
		return "/ativar-conta";
	}

	// Ativar conta no bd
	@GetMapping("/ativarConta")
	public String ativarConta(@RequestParam(name = "token", required = false) String token, RedirectAttributes ra) {

		if (token == "" || token == null) {
			ra.addFlashAttribute("alertErro", "Token de ativação inválido");
			return "redirect:/cadastro/ativar";
		}

		Email email = this.emailService.findByToken(token);

		if (this.emailService.validarVencimento(email)) {
			Usuario usuario = this.usuarioService.findByEmail(email.getEmailDestinatario());
			usuario.setAtivo(true);
			this.usuarioService.save(usuario);
		} else {
			ra.addFlashAttribute("alertErro", "Token de ativação vencido, por favor re-envie o email de ativação");
			return "redirect:/cadastro/ativar";
		}

		ra.addFlashAttribute("alertSucesso", "Conta Ativada com sucesso!");
		return "redirect:/login";
	}

	/*
	 * Reenviar email de confirmação
	 */
	@PostMapping("/cadastro/reenviarConfirmacao")
	public String reenviarEmailConfirmarcaoConta(@RequestParam(name = "email", required = true) String email,
			RedirectAttributes ra) {
		String retorno = "redirect:/cadastro/ativar";

		Usuario usuario = this.usuarioService.findByEmail(email);

		if (usuario == null) {
			ra.addFlashAttribute("alertErro", "Email não cadastrado no sistema");
		} else if (email.trim() != "") {
			try {
				this.usuarioService.reEnviarEmailConfirmacao(usuario.getEmail());
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		} else {
			ra.addFlashAttribute("alertErro", "Email inválido");
		}

		return retorno;
	}
}