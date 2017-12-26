package br.com.armazem21.store.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.com.armazem21.store.model.Tamanho;

public class TamanhoConverter implements Converter<String, Tamanho> {

	@Override
	public Tamanho convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Tamanho tamanho = new Tamanho();
			tamanho.setCodigo(Long.valueOf(codigo));
			return tamanho;
		}
		
		return null;
	}

}
