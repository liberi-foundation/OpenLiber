package br.com.openliber.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.openliber.enums.GeneroEnum;
import br.com.openliber.exception.StorageException;
import br.com.openliber.model.Livro;
import br.com.openliber.model.Usuario;
import br.com.openliber.service.LivroService;

@Controller
@RequestMapping("/livro")
public class LivroController {

	@Autowired
	private LivroService livroService;

	@GetMapping("/upload")
	public ModelAndView exibirForm() {
		ModelAndView mv = new ModelAndView("/livro-form");

		mv.addObject("capa", "/imagem/cover_livro/placeholder.jpg");
		mv.addObject("livro", new Livro());
		mv.addObject("generos", GeneroEnum.values());

		return mv;
	}

	@PostMapping("/upload")
	public String savarLivroEpub(@RequestParam(name = "capaTemp") MultipartFile capa,
			@RequestParam(name = "epubTemp") MultipartFile epub, @Valid @ModelAttribute Livro livro,
			HttpServletRequest request, BindingResult br, RedirectAttributes ra) {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		livro.setAutor(usuario);
		livro.setCapaTemp(capa);
		livro.setEpubTemp(epub);

		if (br.hasErrors()) {
			return "redirect:/livro/upload";
		}

		try {
			this.livroService.salvarLivroEpub(livro);
			return "redirect:/";
		} catch (StorageException e) {
			ra.addFlashAttribute("erro", e.getMessage());
			return "redirect:/livro/upload";
		}

	}
}