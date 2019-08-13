function validarNome(submited) {
	var nome = $("#nome");
	var nomeFeedback = $("#nome-feedback");

	if (submited == false) {
		// Pré validação (sem mensagem)
		if (nome.val().trim().length <= 3 || nome.val().trim().length > 50) {
			inputInvalido(nome, nomeFeedback);
		} else if (nome.val().trim() != "" && nome.val().trim().length > 3 && nome.val().trim().length < 50) {
			// Adicionando classe valida no input
			inputValido(nome, nomeFeedback);
		}
	} else if (submited == true) {
		if (nome.val().trim() == "" || nome.val().trim().length < 3 || nome.val().trim().length > 50) {
			// Sentando mensagens para cada erro
			if (nome.val().trim() == "") {
				inputInvalido(nome, nomeFeedback, "Nome não pode ser um campo vazio");
			} else if (nome.val().trim().length < 3) {
				inputInvalido(nome, nomeFeedback, "Nome não pode ter menos que 3 caracteres");
			} else if (nome.val().trim().length > 50) {
				inputInvalido(nome, nomeFeedback, "Nome não pode ter mais que 50 caracteres");
			}

			return false;
		}

		return true;
	}
}

function validarSobrenome(submited) {
	var sobrenome = $("#sobrenome");
	var sobrenomeFeedback = $("#sobrenome-feedback");

	if (submited == false) {
		// Pré validação (sem mensagem)
		if (sobrenome.val().trim().length <= 3 || sobrenome.val().trim().length > 50) {
			inputInvalido(sobrenome, sobrenomeFeedback);
		} else if (sobrenome.val().trim() != "" && sobrenome.val().trim().length > 3 && sobrenome.val().trim().length < 50) {
			// Adicionando classe valida no input
			inputValido(sobrenome, sobrenomeFeedback);
		}
	} else if (submited == true) {
		if (sobrenome.val().trim() == "" || sobrenome.val().trim().length < 3 || sobrenome.val().trim().length > 50) {
			// Sentando mensagens para cada erro
			if (sobrenome.val().trim() == "") {
				inputInvalido(sobrenome, sobrenomeFeedback, "Sobrenome não pode ser um campo vazio");
			} else if (sobrenome.val().trim().length < 3) {
				inputInvalido(sobrenome, sobrenomeFeedback, "Sobrenome não pode ter menos que 3 caracteres");
			} else if (sobrenome.val().trim().length > 50) {
				inputInvalido(sobrenome, sobrenomeFeedback, "Sobrenome não pode ter mais que 50 caracteres");
			}

			return false;
		}

		return true;
	}

	return false;
}

function validarApelido(submited) {
	var apelido = $("#apelido");
	var apelidoFeedback = $("#apelido-feedback");
	const somenteLetras = /^[A-Za-z]/;

	if (submited == false) {
		// Pré validação (sem mensagem)
		if (!(somenteLetras.test(apelido.val().trim())) && apelido.val().trim() != "") {
			inputInvalido(apelido, apelidoFeedback, "Apelido não pode <b>iniciar<b> com números")
		} else if (apelido.val().trim().length < 3 || apelido.val().trim().length > 40) {
			inputInvalido(apelido, apelidoFeedback);
		} else {
			inputValido(apelido, apelidoFeedback);
		}
	} else {
		if (apelido.val().trim() == "" || apelido.val().trim().length < 3 || apelido.val().trim().length > 40 || !(somenteLetras.test(apelido.val().trim()))) {
			// Setando uma mensagem pra cada erro
			if (apelido.val().trim().length == "") {
				inputInvalido(apelido, apelidoFeedback, "Apilido não pode ser vazio");
			} else if (!( somenteLetras.test( apelido.val().trim()))) {
				inputInvalido(apelido, apelidoFeedback, "Apelido não pode <b>iniciar<b> com números");
			} else if (apelido.val().trim().length < 3) {
				inputInvalido(apelido, apelidoFeedback, "Apelido não pode ter menos que 3 caracteres");
			} else if (apelido.val().trim().length > 40) {
				inputInvalido(apelido, apelidoFeedback, "Apelido não pode ter mais que 50 caracteres");
			}

			return false;
		}

		return true;
	}

	return false;
}

function validarEmail(submited) {
	var email = $("#email");
	var emailFeedback = $("#email-feedback");
	var regexEmail = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;

	if (submited == false) {
		// Pré validação (sem mensagem)
		if (email.val().trim().length >= 3 && email.val().trim().length < 100 && regexEmail.test(email.val())) {
			// Adicionando classe valida no input
			inputValido(email, emailFeedback);
		} else {
			inputInvalido(email, emailFeedback);
		}
	} else if (submited == true) {
		if (email.val().trim() == "" || email.val().trim().length < 3 || email.val().trim().length > 100 || !regexEmail.test(email.val())) {
			// Sentando mensagens para cada erro
			if (email.val().trim() == "") {
				inputInvalido(email, emailFeedback, "Email não pode ser um campo vazio");
			} else if (email.val().trim().length < 3) {
				inputInvalido(email, emailFeedback, "Email não pode ter menos que 3 caracteres");
			} else if (email.val().trim().length > 100) {
				inputInvalido(email, emailFeedback, "Email não pode ter mais que 50 caracteres");
			} else if (!regexEmail(email.val())) {
				inputInvalido(email, emailFeedback, "Email inválido");
			}

			return false;
		}

		return true;
	}

	return false;
}

function validarSenha(submited) {
	var senha = $("#senha");
	var senhaFeedback = $("#senha-feedback");

	if (submited == false) {
		// Pré validação (sem mensagem)
		if (senha.val().trim().length < 8 || senha.val().trim().length > 20) {
			inputInvalido(senha, senhaFeedback);
		} else if (senha.val().trim().length >= 8) {
			// Adicionando classe valida no input
			inputValido(senha, senhaFeedback);
		}
	} else if (submited == true) {
		if (senha.val().trim() == "" || senha.val().trim().length < 8 || senha.val().trim().length > 20) {
			// Sentando mensagens para cada erro
			if (senha.val().trim() == "") {
				inputInvalido(senha, senhaFeedback, "Senha não pode ser um campo vazio");
			} else if (senha.val().trim().length < 8) {
				inputInvalido(senha, senhaFeedback, "Senha não pode ter menos que 8 caracteres");
			} else if (senha.val().trim().length > 20) {
				inputInvalido(senha, senhaFeedback, "senha não pode ter mais que 20 caracteres");
			}

			return false;
		}

		return true;
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
			inputInvalido(confirmarSenha, confirmarSenhaFeedback);
		} else if (senha.val().trim() == confirmarSenha.val().trim()) {
			// Adicionando classe valida no input
			inputValido(confirmarSenha, confirmarSenhaFeedback);
		}
	} else if (submited == true) {
		if (confirmarSenha.val().trim() == "" || senha.val().trim() != confirmarSenha.val().trim()) {
			if (confirmarSenha.val().trim() == "") {
				inputInvalido(confirmarSenha, confirmarSenhaFeedback, "Senha não pode ser um campo vazio");
			} else if (confirmarSenha.val().trim() != senha.val().trim()) {
				inputInvalido(confirmarSenha, confirmarSenhaFeedback, "Senhas não conferem");
			}
		}

		return true;
	}

	return false;
}

function validarNacionalidade(idInput, submited) {
	var input = $("#" + idInput);
	var inputFeedback = $("#" + idInput);
	
	if (submited == false) {
        // Pré Validação
        if (input.val().trim() > 80) {
            inputInvalido(input, inputFeedback);
        } else {
            inputValido(input, inputFeedback);
        }
    } else {
        if (input.val().trim() > 80) {
            inputInvalido(input, inputFeedback, "Este campo não pode ter mais de 40 caracteres");

            return false;
        }

        return true;
    }

    return false;
}

function validarSobre(submited) {
	var sobre = $("#sobre");
	var sobreFeedback = $("#sobre-feedback");

	if (submited == false) {
		// Pré validação
		if (sobre.val().trim().length > 255) {
			inputInvalido(sobre, sobreFeedback, "Sobre não pode ter mais de 255 caracteres. " + (sobre.val().trim().length - 255) + " caracteres a mais");
		} else {
			inputValido(sobre, sobreFeedback, "<span style='color: #000'>Restam: " + (255 - sobre.val().trim().length) + "</span>");
		}
	} else {
		if (sobre.val().trim().length > 255) {
			inputInvalido(sobre, sobreFeedback, "Sobre não pode ter mais de 255 caracteres. " + (sobre.val().trim().length - 255) + " caracteres a mais");

			return false;
		}

		return true;
	}

	return false;
}

function validarFormulario() {
	var apelido = $("#apelido");
	var nome = $("#nome");
	var sobrenome = $("#sobrenome");
	var email = $("#email");
	var senha = $("#senha");
	var confirmarSenha = $("#confirmarSenha");
	var sobre = $("#sobre");
	
	if (!validarApelido(true)) {
		apelido.focus();

		return false;
	} else if (!validarNome(true)) {
		nome.focus();

		return false;
	} else if (!validarSobrenome(true)) {
		sobrenome.focus();

		return false;
	} else if (!validarEmail(true)) {
		email.focus();

		return false;
	} else if (!validarNacionalidade(true)) {
		$("#cidade").focus;

		return false;
	} else if (!validarSobre(true)) {
		sobre.focus();

		return false;
	} else if (!validarSenha(true)) {
		senha.focus();

		return false;
	} else if (!validarConfirmarSenha(true)) {
		confirmarSenha.focus();

		return false;
	}

	return true;
}