package br.com.armazem21.store.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.armazem21.store.model.Cor;
import br.com.armazem21.store.repository.Cores;
import br.com.armazem21.store.service.exception.NomeCorJaCadastradoException;

@Service
public class CadastroCorService {

	@Autowired
	private Cores cores;
	
	@Transactional
	public Cor salvar(Cor cor) {
		Optional<Cor> corOptional = cores.findByNomeIgnoreCase(cor.getNome());
		if (corOptional.isPresent()) {
			throw new NomeCorJaCadastradoException("Nome da cor já cadastrado");
		}
		
		return cores.saveAndFlush(cor);
	}
	
}
