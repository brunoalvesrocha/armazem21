package br.com.armazem21.store.repository.helper.modelo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.armazem21.store.model.Modelo;
import br.com.armazem21.store.repository.filter.ModeloFilter;

public interface ModelosQueries {
	
	public Page<Modelo> filtrar(ModeloFilter filtro, Pageable pageable);
	
}
