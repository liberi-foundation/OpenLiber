package br.com.openliber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.openliber.model.Usuario;
import br.com.openliber.service.UsuarioService;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/cadastro")
	public ModelAndView exibirTelaCadastro() {
		ModelAndView mv = new ModelAndView("/cadastro");
		mv.addObject("usuario", new Usuario());
		return mv;
	}

	@PostMapping("/cadastro")
	public String salvarUsuario(Usuario usuario) {
		try {
			this.usuarioService.criarUsuario(usuario);
		} catch (Exception e) {
			return "redirect:/cadastro?erro";
		}
		
		return "redirect:/cadastro";
	}

}
