package br.com.armazem21.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.armazem21.store.model.Estado;

public interface Estados extends JpaRepository<Estado, Long> {

}
