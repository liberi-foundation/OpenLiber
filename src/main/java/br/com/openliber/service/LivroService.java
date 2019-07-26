package br.com.openliber.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.openliber.DAO.LivroDAO;
import br.com.openliber.exception.ServiceException;
import br.com.openliber.exception.StorageException;
import br.com.openliber.model.Livro;
import br.com.openliber.model.Storage;
import br.com.openliber.model.Usuario;

@Service
public class LivroService {

	@Autowired
	private LivroDAO livroRep;

	// ## Find ##
	public Livro findById(Integer id) {
		return this.livroRep.findByID(id);

	}

	public List<Livro> findAll() {
		return this.livroRep.findAll();
	}

	public List<Livro> findAll(Sort sort) {
		return this.livroRep.findAll(sort);
	}

	public List<Livro>findByTituloContainingIgnoreCase(String titulo){
		return this.livroRep.findByTituloContainingIgnoreCase(titulo);
	}

	public Livro findByEmailOfAutorAndTitulo(String email, String titulo) {
		return this.livroRep.findByEmailOfAutorAndTitulo(email, titulo);
	}

	public List<Livro> findByEmailOfAutor(String email) {
		return this.livroRep.findByEmailOfAutor(email);
	}

	public List<Livro> findLastsByAutor(Usuario usuario, int max) {
		List<Livro> result = this.livroRep.findByEmailOfAutorLasts(usuario.getEmail());

		if (result.size() > max) {
			return result.subList(0, max);
		}

		return result;
	}

	// ## Save ##
	public void save(Livro livro) {
		this.livroRep.save(livro);
	}

	public void salvarLivroEpub(Livro livro) throws StorageException, ServiceException {
		// Gerando token do livro
		livro.setToken(UUID.randomUUID().toString());
		
		if (livro.getAutor() == null) {
			throw new StorageException("Erro ao salvar: Autor ausente");
		}

		if (livro.getCapaTemp() == null || livro.getCapaTemp().getOriginalFilename() == ""
				|| livro.getCapaTemp().getOriginalFilename() == null) {
			throw new StorageException("Selecione uma capa para o livro");
		}

		if (livro.getEpubTemp() == null || livro.getEpubTemp().getOriginalFilename() == ""
				|| livro.getEpubTemp().getOriginalFilename() == null) {
			throw new StorageException("Selecione um livro para enviar");
		}

		Livro other = this.findByEmailOfAutorAndTitulo(livro.getAutor().getEmail(), livro.getTitulo());
		if (other != null) {
			throw new ServiceException("Você já possui um livro com esse nome");
		}

		Storage s = new Storage(livro.getAutor());
		livro.setCapa(s.savarImagem(livro.getCapaTemp()));
		livro.setEpub(s.salvarEPub(livro.getEpubTemp(), livro.getTitulo()));

		this.save(livro);
	}

	public void editarLivro(Livro livro, Usuario usuarioLogado) throws ServiceException {
		if (livro.getAutor().getId() != usuarioLogado.getId()) {
			throw new ServiceException("Você não tem permissão para editar esse livro");
		}

		Livro livroOriginal = this.findById(livro.getId());
		if (!(livroOriginal.getTitulo().equals(livro.getTitulo()))) {
			Livro other = this.findByEmailOfAutorAndTitulo(livro.getAutor().getEmail(), livro.getTitulo());
			if (other != null) {
				throw new ServiceException("Você já possui um livro com esse nome");
			}
		}

		this.livroRep.save(livro);
	}
}
