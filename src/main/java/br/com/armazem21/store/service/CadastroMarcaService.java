package br.com.armazem21.store.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.armazem21.store.model.Marca;
import br.com.armazem21.store.repository.Marcas;
import br.com.armazem21.store.service.exception.NomeMarcaJaCadastradoException;

@Service
public class CadastroMarcaService {

	@Autowired
	private Marcas marcas;
	
	@Transactional
	public Marca salvar(Marca marca) {
		Optional<Marca> marcaOptional = marcas.findByNomeIgnoreCase(marca.getNome());
		if (marcaOptional.isPresent()) {
			throw new NomeMarcaJaCadastradoException("Nome da marca já cadastrado");
		}
		
		return marcas.saveAndFlush(marca);
	}
	
}
