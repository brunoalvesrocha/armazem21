package br.com.armazem21.store.service.exception;

public class NomeCategoriaJaCadastradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NomeCategoriaJaCadastradoException(String message) {
		super(message);
	}

}
