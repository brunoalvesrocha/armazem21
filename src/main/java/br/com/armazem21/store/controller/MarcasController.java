package br.com.armazem21.store.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.armazem21.store.controller.page.PageWrapper;
import br.com.armazem21.store.model.Marca;
import br.com.armazem21.store.repository.Marcas;
import br.com.armazem21.store.repository.filter.MarcaFilter;
import br.com.armazem21.store.service.CadastroMarcaService;
import br.com.armazem21.store.service.exception.NomeMarcaJaCadastradoException;


@Controller
@RequestMapping("/marcas")
public class MarcasController {

	@Autowired
	private CadastroMarcaService cadastroMarcaService;
	
	@Autowired
	private Marcas marcas;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Marca marca) {
		return new ModelAndView("marca/CadastroMarca");
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Marca marca, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(marca);
		}
		
		try {
			cadastroMarcaService.salvar(marca);
		} catch (NomeMarcaJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(marca);
		}
		
		attributes.addFlashAttribute("mensagem", "Marca salvo com sucesso");
		return new ModelAndView("redirect:/marcas/novo");
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Marca marca, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
		
		marca = cadastroMarcaService.salvar(marca);
		return ResponseEntity.ok(marca);
	}
	
	@GetMapping
	public ModelAndView pesquisar(MarcaFilter marcaFilter, BindingResult result
			, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("marca/PesquisaMarcas");
		
		PageWrapper<Marca> paginaWrapper = new PageWrapper<>(marcas.filtrar(marcaFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
}
