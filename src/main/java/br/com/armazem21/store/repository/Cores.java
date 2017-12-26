package br.com.armazem21.store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.armazem21.store.model.Cor;
import br.com.armazem21.store.repository.helper.cor.CoresQueries;

@Repository
public interface Cores extends JpaRepository<Cor, Long>, CoresQueries {

	public Optional<Cor> findByNomeIgnoreCase(String nome);
	
}
