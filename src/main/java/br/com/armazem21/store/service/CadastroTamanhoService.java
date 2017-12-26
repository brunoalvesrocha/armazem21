package br.com.armazem21.store.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.armazem21.store.model.Tamanho;
import br.com.armazem21.store.repository.Tamanhos;
import br.com.armazem21.store.service.exception.NomeTamanhoJaCadastradoException;

@Service
public class CadastroTamanhoService {

	@Autowired
	private Tamanhos tamanhos;
	
	@Transactional
	public Tamanho salvar(Tamanho tamanho) {
		Optional<Tamanho> tamanhoOptional = tamanhos.findByNomeIgnoreCase(tamanho.getNome());
		if (tamanhoOptional.isPresent()) {
			throw new NomeTamanhoJaCadastradoException("Nome do tamanho já cadastrado");
		}
		
		return tamanhos.saveAndFlush(tamanho);
	}
	
}
