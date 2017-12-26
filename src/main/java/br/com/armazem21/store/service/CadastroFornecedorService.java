package br.com.armazem21.store.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.armazem21.store.model.Fornecedor;
import br.com.armazem21.store.repository.Fornecedores;
import br.com.armazem21.store.service.exception.CpfCnpjFornecedorJaCadastradoException;

@Service
public class CadastroFornecedorService {

	@Autowired
	private Fornecedores fornecedores;
	
	@Transactional
	public void salvar(Fornecedor fornecedor) {
		Optional<Fornecedor> fornecedorExistente = fornecedores.findByCpfOuCnpj(fornecedor.getCpfOuCnpjSemFormatacao());
		if (fornecedorExistente.isPresent()) {
			throw new CpfCnpjFornecedorJaCadastradoException("CPF/CNPJ já cadastrado");
		}
		
		fornecedores.save(fornecedor);
	}
	
}
