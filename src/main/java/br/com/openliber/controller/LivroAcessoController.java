package br.com.openliber.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.openliber.model.Livro;
import br.com.openliber.model.LivroAcesso;
import br.com.openliber.model.Usuario;
import br.com.openliber.service.LivroAcessoService;
import br.com.openliber.service.LivroService;
import br.com.openliber.service.UsuarioService;

@Controller
public class LivroAcessoController {
	
	@Autowired
	private LivroService livroService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private LivroAcessoService livroAcessoService;
	

    @PostMapping(value ="/registrarAcesso", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String  registarAcesso( @RequestBody String dados) {
    	JSONObject livroAcessoJson = new JSONObject(dados);
		
    	String autorLivro =(String) livroAcessoJson.get("autorLivro");
    	int idUsuario = (int) livroAcessoJson.get("autorLivro");
    	
    	String owner = autorLivro.split("/")[0];
    	String epub = autorLivro.split("/")[1].split(".epub")[0];
    	
    	Livro livro= this.livroService.findByAutorApelidoAndTitulo(owner, epub);
    	
    	Usuario usuario= this.usuarioService.findById(idUsuario);
    	
    	LivroAcesso livroAcesso = new LivroAcesso();
    	//livroAcesso.setUsuario(usuario);
    	livroAcesso.setLivro(livro);
    	
    	this.livroAcessoService.salvar(livroAcesso);
    	
    	return "{\"success\": \"true\"}";
	}

}
