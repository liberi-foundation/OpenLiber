package br.com.openliber.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import br.com.openliber.enums.TipoUsuarioEnum;

@Entity
public class Usuario {
	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Integer id;

	@NotBlank
	@Size(max = 255)
	@Column(unique = true, nullable = false)
	private String token;

	@NotBlank
	@Size(max = 100)
	@Column(length = 100, nullable = false, unique = true)
	private String email;

	@NotBlank
	@Size(max = 40)
	@Column(length = 40, nullable = false, unique = true)
	private String apelido;

	@NotBlank
	@Size(max = 30)
	@Column(length = 30, nullable = false)
	private String senha;

	@Size(max = 30)
	@Transient
	private String confirmarSenha;

	@Size(max = 50)
	@Column(length = 50)
	private String nome;

	@Size(max = 50)
	@Column(length = 50)
	private String sobrenome;

	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@Embedded
	private Nacionalidade nacionalidade = new Nacionalidade("", "", "");

	@Enumerated(EnumType.ORDINAL)
	private TipoUsuarioEnum tipoUsuario = TipoUsuarioEnum.PADRAO;

	@Transient
	private MultipartFile fotoTemp;

	@Size(max = 255)
	@Column(length = 255)
	private String foto = "/imagem/avatar/no_image.jpg";

	@Size(max = 255)
	@Column(length = 255)
	private String cover = "/imagem/cover_user/galaxia.jpg";

	@Size(max = 255)
	@Column(length = 255)
	private String sobre;

	@NotNull
	@Column(nullable = false)
	private Boolean ativo = false;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Nacionalidade getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(Nacionalidade nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public TipoUsuarioEnum getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuarioEnum tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public MultipartFile getFotoTemp() {
		return fotoTemp;
	}

	public void setFotoTemp(MultipartFile fotoTemp) {
		this.fotoTemp = fotoTemp;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getSobre() {
		return sobre;
	}

	public void setSobre(String sobre) {
		this.sobre = sobre;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}
