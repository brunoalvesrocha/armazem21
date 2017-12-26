var Brewer = Brewer || {};

Brewer.MarcaCadastroRapido = (function() {
	
	function MarcaCadastroRapido() {
		this.modal = $('#modalCadastroRapidoMarca');
		this.botaoSalvar = this.modal.find('.js-modal-cadastro-marca-salvar-btn');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.inputNomeMarca = $('#nomeMarca');
		this.containerMensagemErro = $('.js-mensagem-cadastro-rapido-marca');
	}
	
	MarcaCadastroRapido.prototype.iniciar = function() {
		this.form.on('submit', function(event) { event.preventDefault() });
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this))
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
	}
	
	function onModalShow() {
		this.inputNomeMarca.focus();
	}
	
	function onModalClose() {
		this.inputNomeMarca.val('');
		this.containerMensagemErro.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
	}
	
	function onBotaoSalvarClick() {
		var nomeMarca = this.inputNomeMarca.val().trim();
		$.ajax({
			url: this.url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ nome: nomeMarca }),
			error: onErroSalvandoMarca.bind(this),
			success: onMarcaSalvo.bind(this)
		});
	}
	
	function onErroSalvandoMarca(obj) {
		var mensagemErro = obj.responseText;
		this.containerMensagemErro.removeClass('hidden');
		this.containerMensagemErro.html('<span>' + mensagemErro + '</span>');
		this.form.find('.form-group').addClass('has-error');
	}
	
	function onMarcaSalvo(marca) {
		var comboMarca = $('#marca');
		comboMarca.append('<option value=' + marca.codigo + '>' + marca.nome + '</option>');
		comboMarca.val(marca.codigo);
		this.modal.modal('hide');
	}
	
	return MarcaCadastroRapido;
	
}());

$(function() {
	var marcaCadastroRapido = new Brewer.MarcaCadastroRapido();
	marcaCadastroRapido.iniciar();
});
