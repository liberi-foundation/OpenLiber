package br.com.openliber.enums;

public enum TipoUsuario {

	ADMIN("Admnistrador"),
	PADRAO("Usuário"),
	PREMIUM("Premium");

	public String texto;

	TipoUsuario(String t) {
		texto = t;
	}

}
