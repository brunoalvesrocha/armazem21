var Brewer = Brewer || {};

Brewer.TamanhoCadastroRapido = (function() {
	
	function TamanhoCadastroRapido() {
		this.modal = $('#modalCadastroRapidoTamanho');
		this.botaoSalvar = this.modal.find('.js-modal-cadastro-tamanho-salvar-btn');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.inputNomeTamanho = $('#nomeTamanho');
		this.containerMensagemErro = $('.js-mensagem-cadastro-rapido-tamanho');
	}
	
	TamanhoCadastroRapido.prototype.iniciar = function() {
		this.form.on('submit', function(event) { event.preventDefault() });
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this))
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
	}
	
	function onModalShow() {
		this.inputNomeTamanho.focus();
	}
	
	function onModalClose() {
		this.inputNomeTamanho.val('');
		this.containerMensagemErro.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
	}
	
	function onBotaoSalvarClick() {
		var nomeTamanho = this.inputNomeTamanho.val().trim();
		$.ajax({
			url: this.url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ nome: nomeTamanho }),
			error: onErroSalvandoTamanho.bind(this),
			success: onTamanhoSalvo.bind(this)
		});
	}
	
	function onErroSalvandoTamanho(obj) {
		var mensagemErro = obj.responseText;
		this.containerMensagemErro.removeClass('hidden');
		this.containerMensagemErro.html('<span>' + mensagemErro + '</span>');
		this.form.find('.form-group').addClass('has-error');
	}
	
	function onTamanhoSalvo(tamanho) {
		var comboTamanho = $('#tamanho');
		comboTamanho.append('<option value=' + tamanho.codigo + '>' + tamanho.nome + '</option>');
		comboTamanho.val(tamanho.codigo);
		this.modal.modal('hide');
	}
	
	return TamanhoCadastroRapido;
	
}());

$(function() {
	var tamanhoCadastroRapido = new Brewer.TamanhoCadastroRapido();
	tamanhoCadastroRapido.iniciar();
});
