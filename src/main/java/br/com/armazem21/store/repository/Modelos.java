package br.com.armazem21.store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.armazem21.store.model.Modelo;
import br.com.armazem21.store.repository.helper.modelo.ModelosQueries;

@Repository
public interface Modelos extends JpaRepository<Modelo, Long>, ModelosQueries {

	public Optional<Modelo> findByNomeIgnoreCase(String nome);
	
}
