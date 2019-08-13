function feedbackInvalido(inputFeedback, mensagem) {
	if (!inputFeedback.hasClass("invalid-feedback")) {
		inputFeedback.addClass("invalid-feedback");
	}

	if (mensagem != null) {
		if (inputFeedback.html() != mensagem) {
			inputFeedback.html(mensagem);
		}
	} else {
		inputFeedback.html("");
	}

	if (inputFeedback.hasClass("d-none")) {
		inputFeedback.toggleClass("d-none", "d-block");
	}
}

function inputInvalido(input, inputFeedback, mensagem = null) {
	if(input.hasClass("is-valid")) {
		input.removeClass("is-valid");
	}

	if (!input.hasClass("is-invalid")) {
		input.addClass("is-invalid");
	}

	feedbackInvalido(inputFeedback, mensagem);
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

function feedbackValido(inputFeedback, feedback = null) {
	if (feedback != null) {
		if (inputFeedback.hasClass("d-none")) {
			inputFeedback.toggleClass("d-none", "d-block");
		}

		if (inputFeedback.hasClass("invalid-feedback")) {
			inputFeedback.removeClass("invalid-feedback").addClass("valid-feedback");
		}

		if (!(inputFeedback.hasClass("valid-feedback"))) {
			inputFeedback.addClass("valid-feedback");
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