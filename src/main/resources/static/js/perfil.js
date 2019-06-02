$('.ativador').each(function() {
	$(this).dblclick(function() {
		$(this).hide().prev("input[disabled]").prop("disabled", false).focus();
		var valor = $(this).prev("input").val();
		$(this).prev("input").val("")
		$(this).prev("input").val(valor);
		$(this).focus();
	});
});

$('.ativador').each(function() {
	$(this).dblclick(function() {
		$(this).hide().prev("textarea[disabled]").prop("disabled", false).focus();
		var valor = $(this).prev("textarea").html();
		$(this).prev("textarea").html("")
		$(this).prev("textarea").html(valor);
		$(this).focus();
	});
});