package br.com.armazem21.store.repository.helper.tamanho;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.armazem21.store.model.Tamanho;
import br.com.armazem21.store.repository.filter.TamanhoFilter;

public interface TamanhosQueries {
	
	public Page<Tamanho> filtrar(TamanhoFilter filtro, Pageable pageable);
	
}
