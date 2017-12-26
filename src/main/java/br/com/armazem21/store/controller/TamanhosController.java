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
import br.com.armazem21.store.model.Tamanho;
import br.com.armazem21.store.repository.Tamanhos;
import br.com.armazem21.store.repository.filter.TamanhoFilter;
import br.com.armazem21.store.service.CadastroTamanhoService;
import br.com.armazem21.store.service.exception.NomeTamanhoJaCadastradoException;

@Controller
@RequestMapping("/tamanhos")
public class TamanhosController {

	@Autowired
	private CadastroTamanhoService cadastroTamanhoService;
	
	@Autowired
	private Tamanhos tamanhos;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Tamanho tamanho) {
		return new ModelAndView("tamanho/CadastroTamanho");
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Tamanho tamanho, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(tamanho);
		}
		
		try {
			cadastroTamanhoService.salvar(tamanho);
		} catch (NomeTamanhoJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(tamanho);
		}
		
		attributes.addFlashAttribute("mensagem", "Tamanho salvo com sucesso");
		return new ModelAndView("redirect:/tamanhos/novo");
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Tamanho tamanho, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
		
		tamanho = cadastroTamanhoService.salvar(tamanho);
		return ResponseEntity.ok(tamanho);
	}
	
	@GetMapping
	public ModelAndView pesquisar(TamanhoFilter tamanhoFilter, BindingResult result
			, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("tamanho/PesquisaTamanhos");
		
		PageWrapper<Tamanho> paginaWrapper = new PageWrapper<>(tamanhos.filtrar(tamanhoFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
}
