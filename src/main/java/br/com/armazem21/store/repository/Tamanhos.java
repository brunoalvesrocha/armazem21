package br.com.armazem21.store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.armazem21.store.model.Tamanho;
import br.com.armazem21.store.repository.helper.tamanho.TamanhosQueries;

@Repository
public interface Tamanhos extends JpaRepository<Tamanho, Long>, TamanhosQueries {

	public Optional<Tamanho> findByNomeIgnoreCase(String nome);
	
}
