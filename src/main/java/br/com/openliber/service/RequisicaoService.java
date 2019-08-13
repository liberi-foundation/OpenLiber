package br.com.openliber.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class RequisicaoService {
	public CloseableHttpClient httpClient;

	public RequisicaoService() {
		httpClient = HttpClientBuilder.create().build();
	}

	public void open() {
		httpClient = HttpClientBuilder.create().build();
	}
	
	public void close() {
		try {
			this.httpClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String enviarJson(String url, JSONObject json) throws IOException {
		HttpPost request = new HttpPost(url);
		StringEntity params = new StringEntity(json.toString());
		request.addHeader("content-type", "application/json;charset=ISO-8859-1");
		request.addHeader("Accept", "application/vnd.pagseguro.com.br.v3+xml;charset=ISO-8859-1");
		request.setEntity(params);
		HttpResponse response = this.httpClient.execute(request);

		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity entity = response.getEntity();

			InputStream in = entity.getContent();
			StringBuilder resposta = new StringBuilder();
			Scanner scan = new Scanner(in);
			while (scan.hasNext()) {
				resposta.append(scan.nextLine());
			}
			scan.close();

			return resposta.toString();
		}

		return "Falha na requisição: " + response.getStatusLine().getStatusCode();
	}

	public String get(String url) throws IOException {
		HttpGet request = new HttpGet(url);
		HttpResponse response = this.httpClient.execute(request);

		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity entity = response.getEntity();

			InputStream in = entity.getContent();
			StringBuilder resposta = new StringBuilder();
			Scanner scan = new Scanner(in);
			while (scan.hasNext()) {
				resposta.append(scan.nextLine());
			}
			scan.close();

			return resposta.toString();
		}

		return "Falha na requisição: " + response.getStatusLine().getStatusCode();
	}
}
