package br.com.armazem21.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.armazem21.store.model.Fornecedor;
import br.com.armazem21.store.repository.helper.fornecedor.FornecedoresQueries;

public interface Fornecedores extends JpaRepository<Fornecedor, Long>, FornecedoresQueries {

	public Optional<Fornecedor> findByCpfOuCnpj(String cpfOuCnpj);

	public List<Fornecedor> findByNomeStartingWithIgnoreCase(String nome);

}
