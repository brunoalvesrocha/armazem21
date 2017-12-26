package br.com.armazem21.store.repository.helper.fornecedor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.armazem21.store.model.Fornecedor;
import br.com.armazem21.store.repository.filter.FornecedorFilter;

public interface FornecedoresQueries {

	public Page<Fornecedor> filtrar(FornecedorFilter filtro, Pageable pageable);
	
}
