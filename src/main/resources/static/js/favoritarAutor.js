function favoritarAutor() {
    btn = $('#btn-favoritar');
    var textoAntigo = btn.html();
    btn.attr('disabled', 'true').html('Carregando');
}