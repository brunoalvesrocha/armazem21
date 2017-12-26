package br.com.armazem21.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.armazem21.store.model.Venda;
import br.com.armazem21.store.repository.helper.venda.VendasQueries;

public interface Vendas extends JpaRepository<Venda, Long>, VendasQueries {

}
