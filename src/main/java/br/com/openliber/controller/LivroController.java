package br.com.openliber.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.openliber.DAO.LivroAcessoDAO;
import br.com.openliber.enums.GeneroEnum;
import br.com.openliber.exception.ServiceException;
import br.com.openliber.exception.StorageException;
import br.com.openliber.model.Livro;
import br.com.openliber.model.LivroAcesso;
import br.com.openliber.model.Usuario;
import br.com.openliber.service.LivroAcessoService;
import br.com.openliber.service.LivroService;
import br.com.openliber.service.UsuarioService;

@Controller
public class LivroController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private LivroService livroService;
	
	@Autowired
	private LivroAcessoService livroAcessoService;

	/*
	 * Upload
	 */
	@GetMapping("/upload")
	public ModelAndView exibirForm(Model model) {
		ModelAndView mv = new ModelAndView("/livro-form");

		Livro livro = (Livro) model.asMap().get("livro");
		if (livro != null) {
			mv.addObject("livro", livro);
		} else {
			mv.addObject("livro", new Livro());
		}

		mv.addObject("capa", "/imagem/cover_livro/placeholder.jpg");
		mv.addObject("generos", GeneroEnum.values());

		return mv;
	}

	@PostMapping("/upload")
	public String savarLivroEpub(HttpServletRequest request, @RequestParam(name = "capaTemp") MultipartFile capa,
			@RequestParam(name = "epubTemp") MultipartFile epub, @Valid @ModelAttribute Livro livro, BindingResult br,
			RedirectAttributes ra) {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		livro.setAutor(usuario);
		livro.setCapaTemp(capa);
		livro.setEpubTemp(epub);

		if (br.hasErrors()) {
			ra.addAttribute("livro", livro);
			return "redirect:/upload";
		}

		try {
			this.livroService.salvarLivroEpub(livro);

			return "redirect:/" + livro.getAutor().getApelido() + "/" + livro.getTitulo() + "/preview";
		} catch (StorageException | ServiceException e) {
			ra.addFlashAttribute("erro", e.getMessage());
			return "redirect:/upload";
		}
	}

	/*
	 * VIEW
	 */
	@GetMapping("/{apelido}/{titulo}/preview")
	public ModelAndView exibirLivro(@PathVariable(name = "apelido", required = true) String apelido,
			@PathVariable(name = "titulo", required = true) String titulo, RedirectAttributes ra) {
		ModelAndView mv = new ModelAndView("/livro");

		Livro livro = this.livroService.findByAutorApelidoAndTitulo(apelido, titulo);
		if (livro != null) {
			mv.addObject("livro", livro);
		} else {
			mv.setViewName("redirect:/inicio");
			ra.addFlashAttribute("alertErro", true);
		}
		
		livro.setQtdAcessos(livro.getQtdAcessos() + 1);
		this.livroService.save(livro);

//		LivroAcesso livroAcesso = new LivroAcesso();
//		livroAcesso.setLivro(livro);
//		
//		LocalDateTime agora = LocalDateTime.now();
//		
//		livroAcesso.setData(agora);
//		
//		livroAcessoService.salvar(livroAcesso);
		return mv;
	}

	@GetMapping("/leitor")
	public ModelAndView leitor(@RequestParam String book, RedirectAttributes ra) {
		ModelAndView mv = new ModelAndView("/leitor");

		String owner = book.split("/")[0];
		String epub = book.split("/")[1].split(".epub")[0];

		mv.addObject("autor", owner);
		mv.addObject("titulo", epub);

		Livro livro = this.livroService.findByAutorApelidoAndTitulo(owner, epub);
		if (livro == null) {
			ra.addFlashAttribute("alertErro", "Livro não encontrado");
			mv.setViewName("redirect:/inicio");
		}
		
		

		return mv;
	}

	@GetMapping("/bibi-iframe")
	public String carregarIframe(@RequestParam String book, RedirectAttributes ra) {
		String owner = book.split("/")[0];
		String epub = book.split("/")[1].split(".epub")[0];

		Livro livro = this.livroService.findByAutorApelidoAndTitulo(owner, epub);
		if (livro == null) {
			ra.addFlashAttribute("alertErro", "Livro não encontrado");
			return "redirect:/inicio";
		}

		return "/bibi-iframe";
	}

	/*
	 * EDITAR
	 */
	@GetMapping("/{apelido}/{titulo}/editar")
	public ModelAndView editarForm(@PathVariable(name = "apelido") String apelido,
			@PathVariable(name = "titulo") String titulo, RedirectAttributes ra, HttpSession session,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/livro-form");

		// Verificando se o usuario está logado
		if (session.getAttribute("usuarioLogado") == null) {
			ra.addFlashAttribute("alertErro", "Você precisa está logado para editar um livro");
			ra.addAttribute("acessoNegado", true);
			ra.addAttribute("retorno", "/" + apelido + "/" + titulo + "/editar");

			mv.setViewName("redirect:/login");
			return mv;
		}

		// Pegado usuario logado
		Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

		// pegando usuario dono do livro
		Usuario usuarioOwner = this.usuarioService.findByApelido(apelido.toLowerCase());

		// Uma primeira validaçao para saber se o usuario é o dono do livro
		if (!(usuarioLogado.getId() == usuarioOwner.getId())) {
			ra.addFlashAttribute("alertErro", "Acesso negado");
			mv.setViewName("redirect:/inicio");
			return mv;
		}

		// Procurando livro com o apelido do usuario logado e o titulo passado no path,
		// para validar se o usuario logado é o dono do livro.
		Livro livro = this.livroService.findByAutorApelidoAndTitulo(usuarioLogado.getApelido(), titulo);
		if (livro == null) {
			ra.addFlashAttribute("alertErro", "Não foi possível encontrar o livro");
			mv.setViewName("redirect:/inicio");
		}

		mv.addObject("livro", livro);
		mv.addObject("generos", GeneroEnum.values());

		return mv;
	}

	@PostMapping("/{apelido}/{titulo}/editar")
	public String salvarEdicao(@PathVariable(name = "apelido") String autor,
			@PathVariable(name = "titulo") String titulo, @Valid @ModelAttribute Livro livro, BindingResult br,
			RedirectAttributes ra, HttpSession session, Model model, HttpServletRequest request) {
		// Verificando se o usuario está logado
		if (session.getAttribute("usuarioLogado") == null) {
			ra.addFlashAttribute("alertErro", "Você precisa está logado para editar um livro");
			ra.addAttribute("acessoNegado", true);
			ra.addAttribute("retorno", "/" + autor + "/" + titulo + "/editar");

			return "redirect:/login";
		}

		// Pegado usuario logado
		Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

		// Pegando livro original
		Livro livroOriginal = this.livroService.findById(livro.getId());

		// setando autor do livro
		livro.setAutor(livroOriginal.getAutor());
		livro.setCapa(livroOriginal.getCapa());
		livro.setEpub(livroOriginal.getEpub());

		try {
			this.livroService.editarLivro(livro, usuarioLogado);
			ra.addFlashAttribute("alertSucesso", "Livro editado com sucesso");

			return "redirect:/" + autor + "/" + titulo + "/preview";
		} catch (ServiceException e) {
			ra.addFlashAttribute("erro", e.getMessage());
			model.addAttribute("livro", livro);
			model.addAttribute("generos", GeneroEnum.values());

			return "redirect:/" + autor + "/" + titulo + "/editar";
		}
	}

	/*
	 * Pesquisar
	 */
	@GetMapping("/pesquisa")
	public ModelAndView pesquisarLivros(@RequestParam(required = false, name = "titulo") String titulo) {
		ModelAndView mv = new ModelAndView("/pesquisa-result");

		if (titulo != null) {
			mv.addObject("livros", this.livroService.findByTituloContainingIgnoreCase(titulo));
			mv.addObject("titulo", titulo);
		} else {
			mv.addObject("livros", this.livroService.findAll(Sort.by("id")));
			mv.addObject("titulo", "Todos os livros cadastrados");
		}

		return mv;
	}
}
