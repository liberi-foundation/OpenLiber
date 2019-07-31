package br.com.openliber.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.openliber.model.Usuario;

public class GrupoController {
	@GetMapping("/grupo/novo")
	public ModelAndView novoGrupo(HttpSession session, RedirectAttributes ra) {
		ModelAndView mv = new ModelAndView("/grupo-form");

		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		if (usuarioLogado == null) {
			ra.addFlashAttribute("acessoNegado", true);
			ra.addFlashAttribute("paginaRetorno", "/grupo/novo");

			mv.setViewName("redirect:/login");
			return mv;
		}

		return mv;
	}
}
