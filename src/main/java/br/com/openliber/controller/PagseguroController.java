package br.com.openliber.controller;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.openliber.model.pagseguro.Item;
import br.com.openliber.service.pagseguro.PagseguroService;
import br.com.openliber.service.pagseguro.TransacaoService;

@Controller
@RequestMapping("/pagseguro")
public class PagseguroController {
	@Autowired
	private PagseguroService pagseguroService;

	@Autowired
	private TransacaoService transacaoService;

	@PostMapping(value = "/checkout", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String realizarCheckout(@RequestBody String item) {
		JSONObject itemJson = new JSONObject(item);

		int id = (int) itemJson.get("id");
		String descricao = (String) itemJson.get("descricao");
		double valor = (double) itemJson.get("valor");
		int qtd = (int) itemJson.get("quantidade");
		Item item1 = new Item(id, descricao, valor, qtd);

		try {
			String code = pagseguroService.realizarCheckout(item1);
			return "{\"success\": true, \"code\": \"" + code + "\"}";
		} catch (IOException e) {
			return "{\"success\": false, \"error\":" + e.getMessage() + "}";
		} catch (JAXBException e) {
			e.printStackTrace();
			return "{\"success\": false, \"error\":" + e.getMessage() + "}";
		}
	}

	@GetMapping("/retorno")
	public String retornoPagseguro(@RequestParam String idPagseguro) {

		this.transacaoService.retorno(idPagseguro);

		return "/retorno-pagseguro";
	}
}
