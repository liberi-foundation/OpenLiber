package br.com.openliber.enums;

public enum TipoUsuarioEnum {

	ADMIN("Admnistrador"),
	PADRAO("Usuário"),
	PREMIUM("Premium");

	public String texto;

	TipoUsuarioEnum(String t) {
		texto = t;
	}

}
