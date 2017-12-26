package br.com.armazem21.store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.armazem21.store.model.Marca;
import br.com.armazem21.store.repository.helper.marca.MarcasQueries;

@Repository
public interface Marcas extends JpaRepository<Marca, Long>, MarcasQueries {

	public Optional<Marca> findByNomeIgnoreCase(String nome);
	
}
