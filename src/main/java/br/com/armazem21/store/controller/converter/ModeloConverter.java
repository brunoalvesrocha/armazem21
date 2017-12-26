package br.com.armazem21.store.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.com.armazem21.store.model.Modelo;


public class ModeloConverter implements Converter<String, Modelo> {

	@Override
	public Modelo convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Modelo marca = new Modelo();
			marca.setCodigo(Long.valueOf(codigo));
			return marca;
		}
		
		return null;
	}

}
