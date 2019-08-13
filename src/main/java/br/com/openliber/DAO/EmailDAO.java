package br.com.openliber.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.openliber.model.Email;

public interface EmailDAO extends JpaRepository<Email, Integer> {
	public Email findByToken(String token);
}