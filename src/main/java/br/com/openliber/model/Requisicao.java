package br.com.openliber.model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Requisicao {
	public static String post(String url, String dados) throws IOException {
		// Iniciando requisição, definindo a URL e abrindo a conexão
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		// Editando cabeçalho da requisição
		con.setRequestMethod("POST");
		con.setRequestProperty("content-type", "application/x-www-form-urlencoded; charset=ISO-8859-1");

		// Enviando a requisição via POST
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(dados);
		wr.flush();
		wr.close();

		// Codigo da resposta
		int responseCode = con.getResponseCode();

		// Imprimindo código da resposta
		System.out.println("Response Code : " + responseCode);

		// Pegados dados da resposta
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// Retornando resposta
		return response.toString();
	}

	public static String get(String url) throws IOException {
		// Iniciando requisição, definindo a URL e abrindo a conexão
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		// Editando cabeçalho da requisição
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-type", "application/x-www-form-urlencoded; charset=ISO-8859-1");

		// Enviando a requisição via POST
		con.setDoOutput(true);
		con.connect();

		// Codigo da resposta
		int responseCode = con.getResponseCode();

		// Imprimindo código da resposta
		System.out.println("Código da resposta: " + responseCode);

		// Pegados dados da resposta
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// Retornando resposta
		return response.toString();
	}
	
	public static String gerarPar(String name, String dado) {
		return "&" + name + "=" + dado;
	}

	public static String gerarParUm(String name, String dado) {
		return name + "=" + dado;
	}
}
