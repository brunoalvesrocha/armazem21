package br.com.armazem21.store.repository.helper.marca;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.armazem21.store.model.Marca;
import br.com.armazem21.store.repository.filter.MarcaFilter;

public interface MarcasQueries {
	
	public Page<Marca> filtrar(MarcaFilter filtro, Pageable pageable);
	
}
