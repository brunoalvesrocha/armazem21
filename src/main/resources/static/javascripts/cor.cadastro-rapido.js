var Brewer = Brewer || {};

Brewer.CorCadastroRapido = (function() {
	
	function CorCadastroRapido() {
		this.modal = $('#modalCadastroRapidoCor');
		this.botaoSalvar = this.modal.find('.js-modal-cadastro-cor-salvar-btn');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.inputNomeCor = $('#nomeCor');
		this.containerMensagemErro = $('.js-mensagem-cadastro-rapido-cor');
	}
	
	CorCadastroRapido.prototype.iniciar = function() {
		this.form.on('submit', function(event) { event.preventDefault() });
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this))
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
	}
	
	function onModalShow() {
		this.inputNomeCor.focus();
	}
	
	function onModalClose() {
		this.inputNomeCor.val('');
		this.containerMensagemErro.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
	}
	
	function onBotaoSalvarClick() {
		var nomeCor = this.inputNomeCor.val().trim();
		$.ajax({
			url: this.url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ nome: nomeCor }),
			error: onErroSalvandoCor.bind(this),
			success: onCorSalvo.bind(this)
		});
	}
	
	function onErroSalvandoCor(obj) {
		var mensagemErro = obj.responseText;
		this.containerMensagemErro.removeClass('hidden');
		this.containerMensagemErro.html('<span>' + mensagemErro + '</span>');
		this.form.find('.form-group').addClass('has-error');
	}
	
	function onCorSalvo(cor) {
		var comboCor = $('#cor');
		comboCor.append('<option value=' + cor.codigo + '>' + cor.nome + '</option>');
		comboCor.val(cor.codigo);
		this.modal.modal('hide');
	}
	
	return CorCadastroRapido;
	
}());

$(function() {
	var corCadastroRapido = new Brewer.CorCadastroRapido();
	corCadastroRapido.iniciar();
});
