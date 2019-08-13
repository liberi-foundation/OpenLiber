package br.com.openliber.service.pagseguro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.openliber.DAO.CheckoutSessionDAO;
import br.com.openliber.model.pagseguro.CheckoutSession;

@Service
public class CheckoutSessionService {
	@Autowired
	private CheckoutSessionDAO checkoutSessionRep;

	public void salvar(CheckoutSession checkoutSession) {
		this.checkoutSessionRep.save(checkoutSession);
	}
}
