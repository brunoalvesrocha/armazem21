package br.com.armazem21.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.armazem21.store.model.Grupo;

public interface Grupos extends JpaRepository<Grupo, Long> {

}
