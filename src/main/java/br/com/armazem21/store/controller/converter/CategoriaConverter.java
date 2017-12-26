package br.com.armazem21.store.controller.converter;

import org.springframework.util.StringUtils;

import br.com.armazem21.store.model.Categoria;

import org.springframework.core.convert.converter.Converter;

public class CategoriaConverter implements Converter<String, Categoria> {

	@Override
	public Categoria convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Categoria categoria = new Categoria();
			categoria.setCodigo(Long.valueOf(codigo));
			return categoria;
		}
		
		return null;
	}

}
