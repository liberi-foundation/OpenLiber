package br.com.openliber.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.openliber.model.pagseguro.CheckoutSession;

public interface CheckoutSessionDAO extends JpaRepository<CheckoutSession, Integer> {

}
