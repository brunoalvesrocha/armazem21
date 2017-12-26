package br.com.armazem21.store.service.exception;

public class CpfCnpjFornecedorJaCadastradoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public CpfCnpjFornecedorJaCadastradoException(String message) {
		super(message);
	}

}
