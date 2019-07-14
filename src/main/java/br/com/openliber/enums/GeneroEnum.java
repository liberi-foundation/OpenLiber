package br.com.openliber.enums;

public enum GeneroEnum {
	// Gêneros Literarios
	POESIA("Poesia"),
	SATIRA("Sátira"),
	SONETO("Soneto"),
	
	// Gêneros narrativos
	ROMANCE("Romance"),
	FABULA("Fábula"),
	CONTO("Conto"),
	CRONICA("Crônica"),
	ENSAIO("Ensaio"),
	POESIAEPICA("Poesia Épica"),
	APOCALIPSEZUMBI("Apocalipse Zumbi"),
	AUTOBIOGRAFIA("Autobiografia"),
	FANTASIA("Fantasia"),
	FICCAOCIENTIFICA("Ficção Ciêntifica"),
	HORROR("Horror"),
	LITFANTASTICA("Literatura Fantástica"),
	LITINFATOJUVENIL("Literatura Infanto-Juvenil"),
	LITJOVEMADULTO("Literatura Jovem Adulto"),
	LITGOTICA("Literatura Gótica"),
	METAFICCAO("Metaficção"),
	NEOCRITICA("Neocrítica"),
	PARODIA("Paródia"),
	SUSPENSE("Suspense"),
	VAMPIRISMO("Vampirismo"),
	AUTOAJUDA("Auto-Ajuda"),
	NEGOCIOS("Negócios"),
	ESPIRITUALILTA("Espiritualista"),
	AVENTURA("Aventura"),
	GUERRA("Guerra"),
	DRAMA("Drama"),
	
	// Gêneros dramáticos
	FARSA("Farsa"),
	TRAGEDIA("Tragédia"),
	ELEGIA("Elegia");
	
	
	public String texto;
	
	GeneroEnum(String t) {
		texto = t;
	}
	
	public String getTexto() {
		return this.texto;
	}
}
