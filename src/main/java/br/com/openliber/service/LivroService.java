package br.com.openliber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.openliber.DAO.LivroDAO;
import br.com.openliber.exception.ServiceException;
import br.com.openliber.exception.StorageException;
import br.com.openliber.model.Livro;
import br.com.openliber.model.Storage;

@Service
public class LivroService {

	@Autowired
	private LivroDAO livroRep;

	// ## Find ##
	public List<Livro> findAll() {
		return this.livroRep.findAll();
	}

	public Livro findByEmailOfAutorAndTitulo(String email, String titulo) {
		return this.livroRep.findByEmailOfAutorAndTitulo(email, titulo);
	}

	// ## Save ##
	public void save(Livro livro) {
		this.livroRep.save(livro);
	}

	public void salvarLivroEpub(Livro livro) throws StorageException, ServiceException {
		if (livro.getAutor() == null) {
			throw new StorageException("Erro ao salvar: Autor ausente");
		}
		
		Livro other = this.findByEmailOfAutorAndTitulo(livro.getAutor().getEmail(), livro.getTitulo());
		if (other != null) {
			throw new ServiceException("Você já possui um livro com esse nome");
		}

		Storage s = new Storage(livro.getAutor().getEmail());
		livro.setCapa(s.savarImagem(livro.getCapaTemp()));
		livro.setEpub(s.salvarEPub(livro.getEpubTemp()));

		this.save(livro);
	}
}
