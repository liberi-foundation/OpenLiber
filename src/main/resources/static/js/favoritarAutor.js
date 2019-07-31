function favoritarAutor(usuarioFavoritadoID, usuarioFavoritouID) {
    var btn = $('#btn-favoritar');
    btn.attr('disabled', 'true').html('Carregando');

    var dados = {
        "usuarioFavoritadoID": usuarioFavoritadoID,
        "usuarioFavoritouID": usuarioFavoritouID
    };

    $.ajax({
        url: '/usuario/favoritar',
        contentType: 'application/json; charset=utf-8',
        type: 'POST',
        data: JSON.stringify(dados),
        success: function(data) {
            btn.removeClass('btn-warning').addClass('btn-danger').attr('disabled', 'false');
            console.log(data);
        }
    });
}