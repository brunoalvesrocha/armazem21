package br.com.armazem21.store.service.exception;

public class CpfCnpjClienteJaCadastradoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public CpfCnpjClienteJaCadastradoException(String message) {
		super(message);
	}

}
