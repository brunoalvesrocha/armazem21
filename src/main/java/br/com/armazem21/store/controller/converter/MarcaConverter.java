package br.com.armazem21.store.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.com.armazem21.store.model.Modelo;

public class MarcaConverter implements Converter<String, Modelo> {

	@Override
	public Modelo convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Modelo modelo = new Modelo();
			modelo.setCodigo(Long.valueOf(codigo));
			return modelo;
		}
		
		return null;
	}

}
