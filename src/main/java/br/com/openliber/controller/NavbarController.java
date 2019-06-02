package br.com.openliber.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavbarController {
	
	@GetMapping("/inicio")
	public String index(Model model) {
		return "/index";
	}
	
	@GetMapping("/inicio/logado")
	public String indexLogado() {
		return "/index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "/login";
	}
	
	@GetMapping("/cadastro")
	public String registro() {
		return "/cadastro";
	}
	
	@GetMapping("/perfil")
	public String perfil() {
		return "/perfil";
	}
	
	@GetMapping("/perfil/editar")
	public String editarPerfil() {
		return "/perfil-editar";
	}
	
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
	
	@GetMapping("/livro/novo")
	public String novoLivro() {
		return "/upload";
	}
}
