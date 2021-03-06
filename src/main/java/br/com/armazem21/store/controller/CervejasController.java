package br.com.armazem21.store.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.armazem21.store.controller.page.PageWrapper;
import br.com.armazem21.store.dto.CervejaDTO;
import br.com.armazem21.store.model.Cerveja;
import br.com.armazem21.store.model.Origem;
import br.com.armazem21.store.repository.Categorias;
import br.com.armazem21.store.repository.Cervejas;
import br.com.armazem21.store.repository.Cores;
import br.com.armazem21.store.repository.Estilos;
import br.com.armazem21.store.repository.Fornecedores;
import br.com.armazem21.store.repository.Marcas;
import br.com.armazem21.store.repository.Modelos;
import br.com.armazem21.store.repository.Tamanhos;
import br.com.armazem21.store.repository.filter.CervejaFilter;
import br.com.armazem21.store.service.CadastroCervejaService;
import br.com.armazem21.store.service.exception.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/produtos")
public class CervejasController {
	
	@Autowired
	private Categorias categorias;
	@Autowired
	private Marcas marcas;
	@Autowired
	private Modelos modelos;
	@Autowired
	private Tamanhos tamanhos;
	@Autowired
	private Cores cores;
	@Autowired
	private Fornecedores fornecedores;
	@Autowired
	private Estilos estilos;
	
	@Autowired
	private CadastroCervejaService cadastroCervejaService;
	
	@Autowired
	private Cervejas cervejas;

	@RequestMapping("/novo")
	public ModelAndView novo(Cerveja cerveja) {
		ModelAndView mv = new ModelAndView("produto/CadastroProduto");
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("categorias", categorias.findAll());
		mv.addObject("fornecedores", fornecedores.findAll());
		mv.addObject("marcas", marcas.findAll());
		mv.addObject("modelos", modelos.findAll());
		mv.addObject("tamanhos", tamanhos.findAll());
		mv.addObject("cores", cores.findAll());
		mv.addObject("origens", Origem.values());
		return mv;
	}
	
	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(cerveja);
		}
		
		cadastroCervejaService.salvar(cerveja);
		attributes.addFlashAttribute("mensagem", "Produto salvo com sucesso!");
		return new ModelAndView("redirect:/produtos/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(CervejaFilter cervejaFilter, BindingResult result
			, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("produto/PesquisaProdutos");
		mv.addObject("marcas", marcas.findAll());
		mv.addObject("modelos", modelos.findAll());
		mv.addObject("Origens", Origem.values());
		
		PageWrapper<Cerveja> paginaWrapper = new PageWrapper<>(cervejas.filtrar(cervejaFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<CervejaDTO> pesquisar(String skuOuNome) {
		return cervejas.porSkuOuNome(skuOuNome);
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Cerveja cerveja) {
		try {
			cadastroCervejaService.excluir(cerveja);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Cerveja cerveja) {
		ModelAndView mv = novo(cerveja);
		mv.addObject(cerveja);
		return mv;
	}
	
}
