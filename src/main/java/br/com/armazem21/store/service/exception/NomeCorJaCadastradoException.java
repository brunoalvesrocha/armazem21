package br.com.armazem21.store.service.exception;

public class NomeCorJaCadastradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NomeCorJaCadastradoException(String message) {
		super(message);
	}

}
