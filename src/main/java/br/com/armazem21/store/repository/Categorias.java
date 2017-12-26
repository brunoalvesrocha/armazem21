package br.com.armazem21.store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.armazem21.store.model.Categoria;
import br.com.armazem21.store.repository.helper.categoria.CategoriasQueries;

@Repository
public interface Categorias extends JpaRepository<Categoria, Long>, CategoriasQueries {

	public Optional<Categoria> findByNomeIgnoreCase(String nome);
	
}
