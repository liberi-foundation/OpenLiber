function feedbackInvalido(inputFeedback, mensagem) {
	if (!inputFeedback.hasClass("invalid-feedback")) {
		inputFeedback.addClass("invalid-feedback");
	}

	if (inputFeedback.html() != mensagem) {
		inputFeedback.html(mensagem);
	}

	if (inputFeedback.hasClass("d-none")) {
		inputFeedback.toggleClass("d-none", "d-block");
	}
}

function inputInvalido(input, inputFeedback, mensagem) {
	if(input.hasClass("is-valid")) {
		input.removeClass("is-valid");
	}

	if (!input.hasClass("is-invalid")) {
		input.addClass("is-invalid");
	}

	feedbackInvalido(inputFeedback, mensagem);
}

function feedbackValido(inputFeedback, feedback = null) {
	if (feedback != undefined) {
		if (inputFeedback.hasClass("invalid-feedback")) {
			inputFeedback.removeClass("invalid-feedback");
		}

		if (inputFeedback.html() != feedback) {
			inputFeedback.html(feedback);
		}
	} else {
		if (inputFeedback.hasClass("d-block")) {
			inputFeedback.toggleClass("d-block", "d-none");
		}
	}
}

function inputValido(input, inputFeedback, feedback) {
	if (input.hasClass("is-invalid")) {
		input.removeClass("is-invalid");
	}

	if (!input.hasClass("is-valid")) {
		input.addClass("is-valid");
	}

	feedbackValido(inputFeedback, feedback);
}

function validarTitulo() {
	var titulo = $("#titulo");
	var tituloFeedback = $("#titulo-feedback");

	if (titulo.val().trim().length <= 3) {
		inputInvalido(titulo, tituloFeedback, "Titulo não pode ter menos de 3 caracteres");

		return false;
	} else if (titulo.val().trim().length > 70) {
		inputInvalido(titulo, tituloFeedback, "Titulo não pode ter mais de 50 caracteres");

		return false;
	}

	inputValido(titulo, tituloFeedback);

	return true;
}

function validarSubtitulo() {
	var subtitulo = $("#subtitulo");
	var subtituloFeedback = $("#subtitulo-feedback");

	if (subtitulo.val().trim().length <= 3 && subtitulo.val().trim().length != 0) {
		inputInvalido(subtitulo, subtituloFeedback, "Subtitulo não pode ter menos de 3 caracteres");

		return false;
	} else if (subtitulo.val().trim().length > 150 && subtitulo.val().trim().length != 0) {
		inputInvalido(subtitulo, subtituloFeedback, "Subtitulo não pode ter mais de 50 caracteres");

		return false;
	}

	inputValido(subtitulo, subtituloFeedback);

	return true;
}

function validarEdicao() {
	var edicao = $("#edicao");
	var edicaoFeedback = $("#edicao-feedback");

	if (edicao.val().trim().length <= 3 && edicao.val().trim().length != 0) {
		inputInvalido(edicao, edicaoFeedback, "Edição não pode ter menos de 3 caracteres");
		return false;
	} else if (edicao.val().trim().length > 50 && edicao.val().trim().length != 0) {
		inputInvalido(edicao, edicaoFeedback, "Edição não pode ter mais de 50 caracteres")
		return false;
	}

	inputValido(edicao, edicaoFeedback);
	return true;
}

function validarAnoLancamento() {
	var anoLancamento = $("#anoLancamento");
	var anoLancamentoFeedback = $("#anoLancamento-feedback");

	if (anoLancamento.val().trim().length != 4) {
		inputInvalido(anoLancamento, anoLancamentoFeedback, "Ano de lançamento inválido");
		return false;
	}

	inputValido(anoLancamento, anoLancamentoFeedback);
	return true;
}

function validarNumPaginas() {
	var numPaginas = $("#numPaginas");
	var numPaginasFeedback = $("numPaginas-feedback");

	if (numPaginas.val().trim().length > 4) {
		inputInvalido(numPaginas, numPaginasFeedback, "Número de páginas muito grande");
		return false;
	} else if (numPaginas.val().trim().length < 1) {
		inputInvalido(numPaginas, numPaginasFeedback, "Número de páginas muito pequeno");
		return false;
	}

	inputValido(numPaginas, numPaginasFeedback);
	return true;
}

function validarGenero() {
	var genero = $("#genero");
	var generoOption = $("#genero option:selected");
	var generoFeedback = $("#genero-feedback");
	
	if (generoOption.text().trim() == "" || generoOption.text().trim() == null || generoOption.text().trim() == undefined) {
		inputInvalido(genero, generoFeedback, "selecione um gênero");
		return false;
	}

	inputValido(genero, generoFeedback);
	return true;
}

function validarSinopse() {
	var sinopse = $("#sinopse");
	var sinopseFeedback = $("#sinopse-feedback");

	if (sinopse.val().trim().length <= 3) {
		inputInvalido(sinopse, sinopseFeedback, "Sinopse não pode ter menos de 3 caracteres");
		return false;
	} else if (sinopse.val().trim().length > 255) {
		inputInvalido(sinopse, sinopseFeedback, "Sinopse não pode ter mais de 255 caracteres. " + (sinopse.val().trim().length - 255) + " caracteres a mais");
		return false;
	}

	inputValido(sinopse, sinopseFeedback, "Restam: " + (255 - sinopse.val().trim().length));
	return true;
}

function formSubmit() {
	var form = $("#form-livro");
	var titulo = $("#titulo");
	var subtitulo = $("#subtitulo");
	var edicao = $("#edicao");
	var anoLancamento = $("#anoLancamento");
	var numPaginas = $("#numPaginas");

	if (validarTitulo()) {
		if (validarSubtitulo()) {
			if (validarEdicao()) {
				if (validarAnoLancamento()) {
					if (validarNumPaginas()) {
						if (validarGenero()) {
							if (validarSinopse()) {
								form.submit();
							} else {
								sinopse.focus();
							}
						} else {
							genero.focus();
						}
					} else {
						numPaginas.focus();
					}
				} else {
					anoLancamento.focus();
				}
			} else {
				edicao.focus();
			}
		} else {
			subtitulo.focus();
		}
	} else {
		titulo.focus();
	}
}