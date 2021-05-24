package drinks.exception;

public class FileException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FileException() {
		super("Não foi possível ler o arquivo");
	}

	public FileException(String message) {
		super(message);
	}

}