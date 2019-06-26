package br.com.openliber.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.openliber.model.Mailer;
import br.com.openliber.service.MailerService;

@Controller
@RequestMapping("/email")
public class MailerController {
	private MailerService mailerService;
	
	@GetMapping("/novo")
	public ModelAndView novoEmail() {
		ModelAndView mv = new ModelAndView("/email-form");
		mv.addObject("mailer", new Mailer());
		return mv;
	}
	
	@PostMapping("/enviar")
	public String enviarEmail(
			Mailer mailer, 
			@RequestParam(value="remetenteNome") String nomeRemetente, 
			@RequestParam(value="remententeEmail") String remetenteEmail,
			@RequestParam(value="destinatarioNome") String destinatarioNome,
			@RequestParam(value="destinatarioEmail") String destinatarioEmail) 
	{
		System.out.println(mailer.toString());
		return "redirect:/email/novo?sucesso";
	}
}
