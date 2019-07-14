package br.com.openliber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.openliber.service.LivroService;

@Controller
public class IndexController {

	@Autowired
	private LivroService livroService;

	@GetMapping({"", "/", "inicio"})
	public ModelAndView index(Model model) {
		ModelAndView mv = new ModelAndView("/index");

		mv.addObject("livros", this.livroService.findAll());

		return mv;
	}
}
