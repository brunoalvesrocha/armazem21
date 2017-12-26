package br.com.armazem21.store.repository.helper.cor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.armazem21.store.model.Cor;
import br.com.armazem21.store.repository.filter.CorFilter;

public interface CoresQueries {
	
	public Page<Cor> filtrar(CorFilter filtro, Pageable pageable);
	
}
