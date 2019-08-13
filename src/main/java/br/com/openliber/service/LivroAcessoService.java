package br.com.openliber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.openliber.DAO.LivroAcessoDAO;
import br.com.openliber.model.LivroAcesso;

@Service
public class LivroAcessoService {

	@Autowired
	private LivroAcessoDAO livroAcessoRep;

	public List<LivroAcesso> findAll() {
		return this.livroAcessoRep.findAll();
	}

	public void deleteById(Integer id) {
		this.livroAcessoRep.deleteById(id);
	}

	public void salvar(LivroAcesso livroAcesso) {
		this.livroAcessoRep.save(livroAcesso);
	}
}
