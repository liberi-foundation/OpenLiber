package testes;

import br.com.openliber.model.Storage;

public class StorageTest {
	public static void main(String[] args) {
		Storage storage = new Storage("teste@mail.com");
		System.out.println(storage.getDirBase());
	}
}
