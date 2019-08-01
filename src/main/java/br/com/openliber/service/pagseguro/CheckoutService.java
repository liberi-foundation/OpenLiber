package br.com.openliber.service.pagseguro;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.openliber.model.Requisicao;
import br.com.openliber.model.pagseguro.CheckoutSession;
import br.com.openliber.model.pagseguro.Pagseguro;

@Service
public class CheckoutService {

	@Autowired
	private CheckoutSessionService checkoutSessionService;

	public CheckoutSession realizarCheckout(Pagseguro pagseguro) throws IOException, JAXBException {
		// valores
		String id = Integer.toString(pagseguro.getCheckout().getItem().getId());
		String descricao = pagseguro.getCheckout().getItem().getDescricao();
		String valor = Double.toString(pagseguro.getCheckout().getItem().getValor());
		String qtd = Integer.toString(pagseguro.getCheckout().getItem().getQuantidade());

		// Criando link da requisição
		StringBuilder link = new StringBuilder();
		link.append(pagseguro.getServico());
		link.append(pagseguro.getEndpoint());
		link.append(Requisicao.gerarPar("itemId1", id));
		link.append(Requisicao.gerarPar("itemDescription1", descricao));
		link.append(Requisicao.gerarPar("itemAmount1", valor));
		link.append(Requisicao.gerarPar("itemQuantity1", qtd));

		String xml = Requisicao.get(link.toString());

		// Lendo XML
		JAXBContext jc = JAXBContext.newInstance(CheckoutSession.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		StreamSource streamSource = new StreamSource(new StringReader(xml));

		// Transformando em objeto
		JAXBElement<CheckoutSession> je = unmarshaller.unmarshal(streamSource, CheckoutSession.class);
		CheckoutSession checkoutSession = (CheckoutSession) je.getValue();

		this.checkoutSessionService.salvar(checkoutSession);

		// Retornando checkout
		return checkoutSession;
	}
}
