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
import br.com.armazem21.store.model.Modelo;
import br.com.armazem21.store.repository.Modelos;
import br.com.armazem21.store.repository.filter.ModeloFilter;
import br.com.armazem21.store.service.CadastroModeloService;
import br.com.armazem21.store.service.exception.NomeModeloJaCadastradoException;


@Controller
@RequestMapping("/modelos")
public class ModelosController {

	@Autowired
	private CadastroModeloService cadastroModeloService;
	
	@Autowired
	private Modelos modelos;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Modelo modelo) {
		return new ModelAndView("modelo/CadastroModelo");
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Modelo modelo, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(modelo);
		}
		
		try {
			cadastroModeloService.salvar(modelo);
		} catch (NomeModeloJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(modelo);
		}
		
		attributes.addFlashAttribute("mensagem", "Modelo salvo com sucesso");
		return new ModelAndView("redirect:/modelos/novo");
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Modelo modelo, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
		
		modelo = cadastroModeloService.salvar(modelo);
		return ResponseEntity.ok(modelo);
	}
	
	@GetMapping
	public ModelAndView pesquisar(ModeloFilter modeloFilter, BindingResult result
			, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("modelo/PesquisaModelos");
		
		PageWrapper<Modelo> paginaWrapper = new PageWrapper<>(modelos.filtrar(modeloFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
}
