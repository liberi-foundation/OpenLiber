function favoritarAutor(usuarioFavoritadoID, usuarioFavoritouID) {
    var btn = $('#btn-favoritar');
    btn.attr('disabled', 'true').html('Carregando');

    var dados = {
        "idFavoritado": usuarioFavoritadoID,
        "idFavoritou": usuarioFavoritouID
    };

    $.ajax({
        url: '/usuario/favoritar',
        contentType: 'application/json; charset=utf-8',
        type: 'POST',
        data: JSON.stringify(dados),
        success: function(response) {
        	console.log(response);
        	if (response.success == true) {
        		btn.removeClass('btn-warning').addClass('btn-danger');
                btn.prop('disabled', false);
                btn.html('Desfavoritar');
        	}
        }
    });
}