package br.com.armazem21.store.repository.helper.categoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.armazem21.store.model.Categoria;
import br.com.armazem21.store.repository.filter.CategoriaFilter;

public interface CategoriasQueries {
	
	public Page<Categoria> filtrar(CategoriaFilter filtro, Pageable pageable);
	
}
