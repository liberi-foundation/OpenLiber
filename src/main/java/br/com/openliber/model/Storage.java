package br.com.openliber.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

import br.com.openliber.exception.StorageException;

public class Storage {
	private String dirBase;
	private String dirOwner;
	private boolean isOk = false;
	
	private String dirLivros = "livros";
	private String dirImagens = "imagens";
	private String dirEPubs = "epub";

	public Storage(Usuario dirOwner) {
		this.dirBase = Paths.get(new File("").getAbsolutePath(), "src", "main", "resources", "static").toString();
		if (dirOwner.getEmail() != null) {
			this.dirOwner = dirOwner.getApelido().toString();
			this.isOk = true;
		}
	}
	
	public boolean getIsOk() {
		return this.isOk;
	}

	public String getDirBase() {
		return dirBase;
	}
	
	public String getDirLivros() {
		return this.dirLivros;
	}

	public String getDirImagens() {
		return this.dirImagens;
	}
	
	public String getDirEPubs() {
		return this.dirEPubs;
	}

	public String getDirOwner() {
		return dirOwner;
	}

	public String salvar(String diretorio, MultipartFile arquivo, String nome) throws StorageException {
		Path dirPath = Paths.get(this.getDirBase(), diretorio);
		Path arquivoPath = dirPath.resolve(nome);

		try {
			new File(dirPath.toString()).mkdirs();
			arquivo.transferTo(arquivoPath.toFile());

			return Paths.get(diretorio, nome).toString();
		} catch (IOException e) {
			throw new StorageException("Não foi possivel salvar o arquivo: " + arquivo.getOriginalFilename() + "\n" + e.getMessage());
		}
	}

	public String savarImagem(MultipartFile arquivo) throws StorageException {
		if (this.isOk) {
			String dir = Paths.get("/uploads", this.dirOwner, this.dirImagens).toString();

			return this.salvar(dir, arquivo, arquivo.getOriginalFilename());
		} else {
			throw new StorageException("Arquivo não pode ser salvo");
		}
	}

	public String salvarEPub(MultipartFile arquivo, String nomeDoArquivo) throws StorageException {
		if (this.isOk) {
			String diretorio = Paths.get("/bib", "bookshelf", this.dirOwner).toString();
			return this.salvar(diretorio, arquivo, nomeDoArquivo + ".epub");
		} else {
			throw new StorageException("Arquivo não pode ser salvo");
		}
	}
}
