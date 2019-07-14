package br.com.openliber.controller;

import javax.mail.MessagingException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.openliber.model.Mailer;
import br.com.openliber.service.MailerService;

@Controller
@RequestMapping("/email")
public class MailerController {
	private MailerService mailerService = new MailerService();

	@GetMapping("/novo")
	public ModelAndView novoEmail() {
		ModelAndView mv = new ModelAndView("/email-form");
		mv.addObject("mailer", new Mailer());
		return mv;
	}

	@PostMapping("/enviar")
	public String enviarEmail(Mailer mailer) {
		try {
			this.mailerService.sendEmailTSL(mailer);
		} catch (MessagingException e) {
			e.printStackTrace();
			
			return "redirect:/email/novo?fracasso";
		}

		return "redirect:/email/novo?sucesso";
	}
}
