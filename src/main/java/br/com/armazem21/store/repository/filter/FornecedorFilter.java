package br.com.armazem21.store.repository.filter;

import br.com.armazem21.store.model.TipoPessoa;

public class FornecedorFilter {

	private String nomeDaLoja;
	private String cpfOuCnpj;

	public String getNomeDaLoja() {
		return nomeDaLoja;
	}

	public void setNomeDaLoja(String nome) {
		this.nomeDaLoja = nome;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Object getCpfOuCnpjSemFormatacao() {
		return TipoPessoa.removerFormatacao(this.cpfOuCnpj);
	}

}
