package br.com.openliber.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Livro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_livro")
	private Integer id;

	@Transient
	private MultipartFile capaTemp;
	@Column(nullable = false)
	private String capa;

	@NotBlank(message = "Titulo não pode ser vazio")
	@Column(length = 70, nullable = false)
	private String titulo;

	@Column(length = 150)
	private String subtitulo;

	@Column(length = 50)
	private String edicao;

	@JoinColumn(nullable = false)
	@ManyToOne
	private Usuario autor;

	@Min(value = 0000, message = "Ano de lançamento inválido")
	private Integer anoLancamento;

	@Min(value = 0000, message = "Numero de páginas inválido")
	private Integer numPaginas;

	@NotBlank(message = "Selecione um gênero")
	private String generos;

	@NotBlank(message = "Sinopse não pode ser vazia")
	@Column(nullable = false)
	private String sinopse;

	@Transient
	private MultipartFile epubTemp;

	@Column(nullable = false)
	private String epub;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCapa() {
		return capa;
	}

	public void setCapa(String capa) {
		this.capa = capa;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public Integer getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(Integer anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	public Integer getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(Integer numPaginas) {
		this.numPaginas = numPaginas;
	}

	public String getGeneros() {
		return generos;
	}

	public void setGeneros(String generos) {
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

	public MultipartFile getCapaTemp() {
		return capaTemp;
	}

	public void setCapaTemp(MultipartFile capaTemp) {
		this.capaTemp = capaTemp;
	}

	public MultipartFile getEpubTemp() {
		return epubTemp;
	}

	public void setEpubTemp(MultipartFile epubTemp) {
		this.epubTemp = epubTemp;
	}
}
