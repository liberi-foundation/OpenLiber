package br.com.openliber.service.pagseguro;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.openliber.model.pagseguro.Checkout;
import br.com.openliber.model.pagseguro.CheckoutSession;
import br.com.openliber.model.pagseguro.Item;
import br.com.openliber.model.pagseguro.Pagseguro;

@Service
public class PagseguroService {

	@Autowired
	private CheckoutService checkoutService;

	public String realizarCheckout(Item item) throws IOException, JAXBException {
		Checkout checkout = new Checkout(item);
		checkout.setTimeout(25);

		Pagseguro pagseguro = new Pagseguro("https://ws.sandbox.pagseguro.uol.com.br");
		pagseguro.setEndpoint("/v2/checkout");
		pagseguro.setCheckout(checkout);

		CheckoutSession checkoutSession = this.checkoutService.realizarCheckout(pagseguro);

		return checkoutSession.getCode();
	}
}
