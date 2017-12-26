package br.com.armazem21.store.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.com.armazem21.store.model.Cor;

public class CorConverter implements Converter<String, Cor> {

	@Override
	public Cor convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Cor cor = new Cor();
			cor.setCodigo(Long.valueOf(codigo));
			return cor;
		}
		
		return null;
	}

}
