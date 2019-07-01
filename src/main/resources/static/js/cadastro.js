function validarNome(submited) {
	var nome = $("#nome");
	var nomeFeedback = $("#nome-feedback");

	if (submited == false) {
		// Pré validação (sem mensagem)
		if (nome.val().trim().length <= 3) {
			if (nome.hasClass("is-valid")) {
				nome.removeClass("is-valid");
			}

			if (nomeFeedback.hasClass("invalid-feedback")) {
				nomeFeedback.html("Nome não pode ter menos que 3 caracteres");
			}
		} else if (nome.val().trim().length > 50) {
			if (!nome.hasClass("is-invalid")) {
				if (nome.hasClass("is-valid")) {
					nome.removeClass("is-valid");
				}
				nome.addClass("is-invalid");
			}
		} else if (nome.val().trim() != "" && nome.val().trim().length > 3 && nome.val().trim().length < 50) {
			// Adicionando classe valida no input
			if (!nome.hasClass("is-valid")) {
				if (nome.hasClass("is-invalid")) {
					nome.removeClass("is-invalid");
				}
				nome.addClass("is-valid");
			}

			if (nomeFeedback.hasClass("d-block")) {
				nomeFeedback.toggleClass("d-block", "d-none");
			}
		}
	} else if (submited == true) {
		if (nome.val().trim() == "" || nome.val().trim().length < 3 || nome.val().trim().length > 50) {
			// Exibindo feedback
			if (nomeFeedback.hasClass("d-none")) {
				nomeFeedback.toggleClass("d-none", "d-block");
			}

			// Adicionando classe invalida no feedback
			if (!nomeFeedback.hasClass("invalid-feedback")) {
				nomeFeedback.addClass("invalid-feedback");
			}

			// Removendo classe valida do input
			if (nome.hasClass("is-valid")) {
				nome.removeClass("is-valid");
			}

			// Adicionando classe invalida do input
			if (!nome.hasClass("is-invalid")) {
				nome.addClass("is-invalid");
			}

			// Sentando mensagens para cada erro
			if (nome.val().trim() == "") {
				nomeFeedback.html("Nome não pode ser um campo vazio");
			} else if (nome.val().trim().length < 3) {
				nomeFeedback.html("Nome não pode ter menos que 3 caracteres");
			} else if (nome.val().trim().length > 50) {
				nomeFeedback.html("Nome não pode ter mais que 50 caracteres")
			}

			return false;
		} else {
			// Escondendo feedback
			if (!nomeFeedback.hasClass("d-none")) {
				nomeFeedback.toggleClass("d-none", "d-block");
			}

			// Removendo classe invalida do input
			if (nome.hasClass("is-invalid")) {
				nome.removeClass("is-invalid");
			}

			// Adicionando classe valida no input
			if (!nome.hasClass("is-valid")) {
				nome.addClass("is-valid");
			}

			return true;
		}
	}
}

function validarSobrenome(submited) {
	var sobrenome = $("#sobrenome");
	var sobrenomeFeedback = $("#sobrenome-feedback");

	if (submited == false) {
		// Pré validação (sem mensagem)
		if (sobrenome.val().trim().length <= 3) {
			if (sobrenome.hasClass("is-valid")) {
				sobrenome.removeClass("is-valid");
			}

			if (sobrenomeFeedback.hasClass("invalid-feedback")) {
				sobrenomeFeedback.html("Sobrenome não pode ter menos que 3 caracteres");
			}
		} else if (sobrenome.val().trim().length > 50) {
			if (!sobrenome.hasClass("is-invalid")) {
				if (sobrenome.hasClass("is-valid")) {
					sobrenome.removeClass("is-valid");
				}
				sobrenome.addClass("is-invalid");
			}
		} else if (sobrenome.val().trim() != "" && sobrenome.val().trim().length > 3 && sobrenome.val().trim().length < 50) {
			// Adicionando classe valida no input
			if (!sobrenome.hasClass("is-valid")) {
				if (sobrenome.hasClass("is-invalid")) {
					sobrenome.removeClass("is-invalid");
				}
				sobrenome.addClass("is-valid");
			}

			if (nomeFeedback.hasClass("d-block")) {
				nomeFeedback.toggleClass("d-block", "d-none");
			}
		}
	} else if (submited == true) {
		if (sobrenome.val().trim() == "" || sobrenome.val().trim().length < 3 || sobrenome.val().trim().length > 50) {
			// Exibindo feedback
			if (sobrenomeFeedback.hasClass("d-none")) {
				sobrenomeFeedback.toggleClass("d-none", "d-block");
			}

			// Adicionando classe invalida no feedback
			if (!sobrenomeFeedback.hasClass("invalid-feedback")) {
				sobrenomeFeedback.addClass("invalid-feedback");
			}

			// Removendo classe valida do input
			if (sobrenome.hasClass("is-valid")) {
				sobrenome.removeClass("is-valid");
			}

			// Adicionando classe invalida do input
			if (!sobrenome.hasClass("is-invalid")) {
				sobrenome.addClass("is-invalid");
			}

			// Sentando mensagens para cada erro
			if (sobrenome.val().trim() == "") {
				sobrenomeFeedback.html("Sobrenome não pode ser um campo vazio");
			} else if (sobrenome.val().trim().length < 3) {
				sobrenomeFeedback.html("Sobrenome não pode ter menos que 3 caracteres");
			} else if (sobrenome.val().trim().length > 50) {
				sobrenomeFeedback.html("Sobrenome não pode ter mais que 50 caracteres")
			}

			return false;
		} else {
			// Escondendo feedback
			if (!sobrenomeFeedback.hasClass("d-none")) {
				sobrenomeFeedback.toggleClass("d-none", "d-block");
			}

			// Removendo classe invalida do input
			if (sobrenome.hasClass("is-invalid")) {
				sobrenome.removeClass("is-invalid")
			}

			// Adicionando classe valida no input
			if (!sobrenome.hasClass("is-valid")) {
				sobrenome.addClass("is-valid");
			}

			return true;
		}
	}

	return false;
}

function validarEmail(submited) {
	var email = $("#email");
	var emailFeedback = $("#email-feedback");
	var regexEmail = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;

	if (submited == false) {
		// Pré validação (sem mensagem)
		if (email.val().trim().length <= 3) {
			if (email.hasClass("is-valid")) {
				email.removeClass("is-valid");
			}

			if (emailFeedback.hasClass("invalid-feedback")) {
				emailFeedback.html("Email não pode ter menos que 3 caracteres");
			}
		} else if (email.val().trim().length > 100) {
			if (!email.hasClass("is-invalid")) {
				if (email.hasClass("is-valid")) {
					email.removeClass("is-valid");
				}
				email.addClass("is-invalid");
			}
		} else if (email.val().trim().length < 100 && regexEmail.test(email.val())) {
			// Adicionando classe valida no input
			if (!email.hasClass("is-valid")) {
				if (email.hasClass("is-invalid")) {
					email.removeClass("is-invalid");
				}
				email.addClass("is-valid");
			}

			if (emailFeedback.hasClass("d-block")) {
				emailFeedback.toggleClass("d-block", "d-none");
			}
		} else {
			if (emailFeedback.hasClass("invalid-feedback")) {
				emailFeedback.html("Email inválido");
			}
		}
	} else if (submited == true) {
		if (email.val().trim() == "" || email.val().trim().length < 3 || email.val().trim().length > 100 || !regexEmail.test(email.val())) {
			// Exibindo feedback
			if (emailFeedback.hasClass("d-none")) {
				emailFeedback.toggleClass("d-none", "d-block");
			}

			// Adicionando classe invalida no feedback
			if (!emailFeedback.hasClass("invalid-feedback")) {
				emailFeedback.addClass("invalid-feedback");
			}

			// Removendo classe valida do input
			if (email.hasClass("is-valid")) {
				email.removeClass("is-valid");
			}

			// Adicionando classe invalida do input
			if (!email.hasClass("is-invalid")) {
				email.addClass("is-invalid");
			}

			// Sentando mensagens para cada erro
			if (email.val().trim() == "") {
				emailFeedback.html("Email não pode ser um campo vazio");
			} else if (email.val().trim().length < 3) {
				emailFeedback.html("Email não pode ter menos que 3 caracteres");
			} else if (email.val().trim().length > 100) {
				emailFeedback.html("Email não pode ter mais que 50 caracteres")
			} else if (!regexEmail(email.val())) {
				emailFeedback.html("Email inválido");
			}

			return false;
		} else {
			// Escondendo feedback
			if (!emailFeedback.hasClass("d-none")) {
				emailFeedback.toggleClass("d-none", "d-block");
			}

			// Removendo classe invalida do input
			if (email.hasClass("is-invalid")) {
				email.removeClass("is-invalid")
			}

			// Adicionando classe valida no input
			if (!email.hasClass("is-valid")) {
				email.addClass("is-valid");
			}

			return true;
		}
	}

	return false;
}

function validarSenha(submited) {
	var senha = $("#senha");
	var senhaFeedback = $("#senha-feedback")

	if (submited == false) {
		// Pré validação (sem mensagem)
		if (senha.val().trim().length < 8) {
			if (senha.hasClass("is-valid")) {
				senha.removeClass("is-valid");
			}

			if (senhaFeedback.hasClass("invalid-feedback")) {
				senhaFeedback.html("Senha deve conter no minimo 8 caracteres");
			}
		} else if (senha.val().trim().length > 20) {
			if (!senha.hasClass("is-invalid")) {
				if (senha.hasClass("is-valid")) {
					senha.removeClass("is-valid");
				}
				senha.addClass("is-invalid");
			}
		} else if (senha.val().trim().length >= 8) {
			// Adicionando classe valida no input
			if (!senha.hasClass("is-valid")) {
				if (senha.hasClass("is-invalid")) {
					senha.removeClass("is-invalid");
				}
				senha.addClass("is-valid");
			}

			if (senhaFeedback.hasClass("d-block")) {
				senhaFeedback.toggleClass("d-block", "d-none");
			}
		}
	} else if (submited == true) {
		if (senha.val().trim() == "" || senha.val().trim().length < 8 || senha.val().trim().length > 20) {
			// Exibindo feedback
			if (senhaFeedback.hasClass("d-none")) {
				senhaFeedback.toggleClass("d-none", "d-block");
			}

			// Adicionando classe invalida no feedback
			if (!senhaFeedback.hasClass("invalid-feedback")) {
				senhaFeedback.addClass("invalid-feedback");
			}

			// Removendo classe valida do input
			if (senha.hasClass("is-valid")) {
				senha.removeClass("is-valid");
			}

			// Adicionando classe invalida do input
			if (!senha.hasClass("is-invalid")) {
				senha.addClass("is-invalid");
			}

			// Sentando mensagens para cada erro
			if (senha.val().trim() == "") {
				senhaFeedback.html("Senha não pode ser um campo vazio");
			} else if (senha.val().trim().length < 8) {
				senhaFeedback.html("Senha não pode ter menos que 8 caracteres");
			} else if (senha.val().trim().length > 20) {
				senhaFeedback.html("senha não pode ter mais que 20 caracteres")
			}

			return false;
		} else {
			// Escondendo feedback
			if (!senhaFeedback.hasClass("d-none")) {
				senhaFeedback.toggleClass("d-none", "d-block");
			}

			// Removendo classe invalida do input
			if (senha.hasClass("is-invalid")) {
				senha.removeClass("is-invalid")
			}

			// Adicionando classe valida no input
			if (!senha.hasClass("is-valid")) {
				senha.addClass("is-valid");
			}

			return true;
		}
	}

	return false;
}

function validarConfirmarSenha(submited) {
	var senha = $("#senha");
	var confirmarSenha = $("#confirmarSenha");
	var confirmarSenhaFeedback = $("#confirmarSenha-feedback");

	if (submited == false) {
		// Pré validação (sem mensagem)
		if (senha.val().trim() != confirmarSenha.val().trim()) {
			if (confirmarSenha.hasClass("is-valid")) {
				confirmarSenha.removeClass("is-valid");
			}
			confirmarSenha.addClass("is-invalid");

			if (confirmarSenhaFeedback.hasClass("invalid-feedback")) {
				confirmarSenhaFeedback.html("Senhas não conferem");
			}
		} else if (senha.val().trim() == confirmarSenha.val().trim()) {
			// Adicionando classe valida no input
			if (!confirmarSenha.hasClass("is-valid")) {
				if (confirmarSenha.hasClass("is-invalid")) {
					confirmarSenha.removeClass("is-invalid");
				}
				confirmarSenha.addClass("is-valid");
			}

			if (confirmarSenhaFeedback.hasClass("d-block")) {
				confirmarSenhaFeedback.toggleClass("d-block", "d-none");
			}
		}
	} else if (submited == true) {
		if (confirmarSenha.val().trim() == "" || senha.val().trim() != confirmarSenha.val().trim()) {
			// Exibindo feedback
			if (confirmarSenhaFeedback.hasClass("d-none")) {
				confirmarSenhaFeedback.toggleClass("d-none", "d-block");
			}

			// Adicionando classe invalida no feedback
			if (!confirmarSenhaFeedback.hasClass("invalid-feedback")) {
				confirmarSenhaFeedback.addClass("invalid-feedback");
			}

			// Removendo classe valida do input
			if (confirmarSenha.hasClass("is-valid")) {
				confirmarSenha.removeClass("is-valid");
			}

			// Adicionando classe invalida do input
			if (!confirmarSenha.hasClass("is-invalid")) {
				confirmarSenha.addClass("is-invalid");
			}

			if (confirmarSenha.val().trim() == "") {
				confirmarSenhaFeedback.html("Senha não pode ser um campo vazio");
			} else if (confirmarSenha.val().trim() != senha.val().trim()) {
				confirmarSenhaFeedback.html("Senhas não conferem");
			}
		} else {
			// Escondendo feedback
			if (!confirmarSenhaFeedback.hasClass("d-none")) {
				confirmarSenhaFeedback.toggleClass("d-none", "d-block");
			}

			// Removendo classe invalida do input
			if (confirmarSenha.hasClass("is-invalid")) {
				confirmarSenha.removeClass("is-invalid")
			}

			// Adicionando classe valida no input
			if (!confirmarSenha.hasClass("is-valid")) {
				confirmarSenha.addClass("is-valid");
			}

			return true;
		}
	}

	return false;
}

function validarFormulario() {
	var nome = $("#nome");
	var sobrenome = $("#sobrenome");
	var email = $("#email");
	var senha = $("#senha");
	var confirmarSenha = $("#confirmarSenha");

	if (!validarNome(true)) {
		validarSobrenome(true);
		validarEmail(true);
		validarSenha(true);
		validarConfirmarSenha(true);

		nome.focus();
		return false;
	} else if (!validarSobrenome(true)) {
		validarEmail(true);
		validarSenha(true);
		validarConfirmarSenha(true);

		sobrenome.focus();
		return false;
	} else if (!validarEmail(true)) {
		validarSenha(true);
		validarConfirmarSenha(true);

		email.focus();
		return false;
	} else if (!validarSenha(true)) {
		validarConfirmarSenha(true);

		senha.focus();
		return false;
	} else if (!validarConfirmarSenha(true)) {
		confirmarSenha.focus();
		return false;
	}

	return true;
}