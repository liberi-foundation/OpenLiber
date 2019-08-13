package br.com.openliber.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.openliber.model.LivroAcesso;

public interface LivroAcessoDAO extends JpaRepository<LivroAcesso, Integer> {
	
	public List<LivroAcesso>findAll();
	
	public void deleteById(Integer id);

}
