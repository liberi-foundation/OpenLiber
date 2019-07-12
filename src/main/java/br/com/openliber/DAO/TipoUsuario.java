package br.com.openliber.DAO;

public enum TipoUsuario {

	ADMIN("Admnistrador"), PADRAO("Usuário");

	public String texto;

	TipoUsuario(String t) {
		texto = t;
	}

}
