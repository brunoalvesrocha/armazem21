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
import br.com.armazem21.store.model.Cor;
import br.com.armazem21.store.repository.Cores;
import br.com.armazem21.store.repository.filter.CorFilter;
import br.com.armazem21.store.service.CadastroCorService;
import br.com.armazem21.store.service.exception.NomeCorJaCadastradoException;

@Controller
@RequestMapping("/cores")
public class CoresController {

	@Autowired
	private CadastroCorService cadastroCorService;
	
	@Autowired
	private Cores cores;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Cor cor) {
		return new ModelAndView("cor/CadastroCor");
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Cor cor, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(cor);
		}
		
		try {
			cadastroCorService.salvar(cor);
		} catch (NomeCorJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(cor);
		}
		
		attributes.addFlashAttribute("mensagem", "Cor salvo com sucesso");
		return new ModelAndView("redirect:/cores/novo");
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Cor cor, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
		
		cor = cadastroCorService.salvar(cor);
		return ResponseEntity.ok(cor);
	}
	
	@GetMapping
	public ModelAndView pesquisar(CorFilter corFilter, BindingResult result
			, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("cor/PesquisaCores");
		
		PageWrapper<Cor> paginaWrapper = new PageWrapper<>(cores.filtrar(corFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
}
