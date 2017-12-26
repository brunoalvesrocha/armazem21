package br.com.armazem21.store.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.armazem21.store.model.Modelo;
import br.com.armazem21.store.repository.Modelos;
import br.com.armazem21.store.service.exception.NomeModeloJaCadastradoException;

@Service
public class CadastroModeloService {

	@Autowired
	private Modelos modelos;
	
	@Transactional
	public Modelo salvar(Modelo modelo) {
		Optional<Modelo> modeloOptional = modelos.findByNomeIgnoreCase(modelo.getNome());
		if (modeloOptional.isPresent()) {
			throw new NomeModeloJaCadastradoException("Nome do modelo já cadastrado");
		}
		
		return modelos.saveAndFlush(modelo);
	}
	
}
