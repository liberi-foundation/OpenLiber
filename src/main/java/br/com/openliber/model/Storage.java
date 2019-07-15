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

	private String dirLivros = "livros";
	private String dirImagens = "imagens";
	private String dirEPubs = "epub";

	public Storage(String dirOwner) {
		this.dirBase = Paths.get(new File("").getAbsolutePath(), "src", "main", "resources", "static", "uploads").toString();
		this.dirOwner = dirOwner;
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

	public String salvar(String diretorio, MultipartFile arquivo) throws StorageException {
		Path dirPath = Paths.get(this.getDirBase(), this.getDirOwner(), diretorio);
		Path arquivoPath = dirPath.resolve(arquivo.getOriginalFilename());

		try {
			new File(dirPath.toString()).mkdirs();
			arquivo.transferTo(arquivoPath.toFile());

			return Paths.get("uploads", this.getDirOwner(), diretorio, arquivo.getOriginalFilename()).toString();
		} catch (IOException e) {
			throw new StorageException("Não foi possivel salvar o arquivo: " + arquivo.getOriginalFilename() + "\n" + e.getMessage());
		}
	}

	public String savarImagem(MultipartFile arquivo) throws StorageException {
		return this.salvar(this.dirImagens, arquivo);
	}

	public String salvarEPub(MultipartFile arquivo) throws StorageException {
		String diretorio = Paths.get(this.getDirLivros(), this.getDirEPubs()).toString();
		return this.salvar(diretorio, arquivo);
	}
}
