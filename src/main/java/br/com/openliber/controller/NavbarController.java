package br.com.openliber.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.openliber.DAO.LivroDAO;
import br.com.openliber.model.Livro;

@Controller
public class NavbarController {
	
	@Autowired private LivroDAO livros;
	
	@GetMapping("/livro")
	public String livro() {
		return "/livro";
	}
	
	@GetMapping("/livro/logado")
	public String livroLogado() {
		return "/livro";
	}
	
	@GetMapping("/assinar")
	public String sejaPremium() {
		return "seja-premium";
	}
	
	@GetMapping("/assinar/logado")
	public String sejaPremiumLogado() {
		return "seja-premium";
	}
	
	@GetMapping("/popularidade")
	public ModelAndView popularidadeDosLivros() {
		ModelAndView mv = new ModelAndView("visualizarPopularidadeLivros");
		
		List<Livro> livrosMaisAcessados = this.livros.listarOs7MaisAcessados();
		
		List<String> titulos = new ArrayList<>();
		livrosMaisAcessados.forEach(livro -> titulos.add(livro.getTitulo()));
		
		List<Integer> acessos = new ArrayList<>();
		for (Livro l : livrosMaisAcessados) {
			acessos.add(l.getQtdAcessos());
		}
		
		mv.addObject("titulos", titulos);
		mv.addObject("acessos", acessos);
		
		return mv;
	}
	
	@GetMapping("/livro/novo")
	public String novoLivro(Model model) {
		model.addAttribute("capa", "/imagem/cover_livro/placeholder.jpg");
		model.addAttribute("selecionado", "");
		return "/livro-form";
	}
	
	@GetMapping("/livro/editar")
	public String editarLivro(Model model) {
		model.addAttribute("capa", "/imagem/cover_livro/eloquent_javascript.png");
		model.addAttribute("titulo", "Eloquent Javascript");
		model.addAttribute("autor", "Marijn Haverbeke");
		model.addAttribute("ano", "2017");
		model.addAttribute("nPaginas", "255");
		model.addAttribute("selecionado", "selected");
		model.addAttribute("sinopse", "This is a book about JavaScript, programming, and the wonders of the digital. You can read it online here, or get your own paperback copy.\r\n" + 
				"\r\n" + 
				"Atualmente, estamos melhorando o que já está traduzido, focando na qualidade e precisão da tradução e entendimento do texto como um todo, além de tentar aplicar a gramática mais correta possível. Vários contribuidores ajudaram em diferentes partes do livro e, por isso, existem diversas oportunidades de melhorias.");
		return "/livro-form";
	}
	
	@GetMapping("/grupos")
	public String grupos() {
		return "/grupos";
	}
	
	@GetMapping("/grupos/logado")
	public String gruposLogado() {
		return "/grupos";
	}
	
	@GetMapping("/grupo/Nome-do-Grupo")
	public String grupo() {
		return "/grupo";
	}
	
	@GetMapping("/grupo/Nome-do-Grupo/logado")
	public String grupoLogado() {
		return "/grupo";
	}
	
	@GetMapping("/grupo/novo")
	public String novoGrupo() {
		return "grupo-form";
	}
	
	@GetMapping("/grupo/editar")
	public String novoGrupo(Model model) {
		model.addAttribute("nome", "Nome do Grupo");
		model.addAttribute("descricaoCurta", "Descrição do grupo, isto é uma descrição. Aqui tem coisas descritivas, que descrevem coisas. Descrevendo coisas.");
		model.addAttribute("descricaoLonga", "Descrição 2, descrição mais extensa.\r\n" + 
				"Aqui vai uma descrição mais extensa do grupo, pode conter regras e outras coisas.");
		return "grupo-form";
	}
}
