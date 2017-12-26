package br.com.armazem21.store.repository.helper.cerveja;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.armazem21.store.dto.CervejaDTO;
import br.com.armazem21.store.dto.ValorItensEstoque;
import br.com.armazem21.store.model.Cerveja;
import br.com.armazem21.store.repository.filter.CervejaFilter;

public interface CervejasQueries {

	public Page<Cerveja> filtrar(CervejaFilter filtro, Pageable pageable);
	
	public List<CervejaDTO> porSkuOuNome(String skuOuNome);
	
	public ValorItensEstoque valorItensEstoque();
	
}
