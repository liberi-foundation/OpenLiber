package br.com.openliber.model;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import br.com.openliber.enums.Genero;

@Entity
public class Livro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_livro")
	private Integer id;

	@Lob
	private Byte[] capa;
	private String titulo;
	private LocalDate anoLancamento;
	private Integer numPaginas;
	private ArrayList<Genero> generos;
	private String sinopse;
	private String epub;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Byte[] getCapa() {
		return capa;
	}

	public void setCapa(Byte[] capa) {
		this.capa = capa;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDate getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(LocalDate anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	public Integer getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(Integer numPaginas) {
		this.numPaginas = numPaginas;
	}

	public ArrayList<Genero> getGeneros() {
		return generos;
	}

	public void setGeneros(ArrayList<Genero> generos) {
		this.generos = generos;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public String getEpub() {
		return epub;
	}

	public void setEpub(String epub) {
		this.epub = epub;
	}
}
