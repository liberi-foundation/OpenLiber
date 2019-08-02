function checkout(id, descricao, valor, quantidade, btn) {
    var textoAnterior = btn.html();
    btn.attr('disabled', 'true').html('Carregando');

    var item = {
        "id": id,
        "descricao": descricao,
        "valor": valor,
        "quantidade": quantidade
    }

    console.log('item enviado:');
    console.log(item);
    $.ajax({
        url: '/pagseguro/checkout',
        contentType: 'application/json; charset=utf-8',
        type: 'POST',
        data: JSON.stringify(item)
    }).done(function (response) {
        console.log(response);
        if (response.success == true) {
            var url = 'https://sandbox.pagseguro.uol.com.br/v2/checkout/payment.html?code=' + response.code;
            window.open(url, '_blank');
            btn.prop('disabled', false);
            btn.html('<a href="' + url + '" target="_blank">'+ textoAnterior +'</a>');
            btn.attr('onclick', '');
        } else {
            btn.prop('disabled', false);
            btn.html('erro, tente novamente');
        }
    }).fail(function (jqXHR, textStatus, errorThrown) {
        console.log(jqXHR.status);
        console.log(textStatus);
        console.log(errorThrown);
        btn.prop('disabled', false);
        btn.html('erro, tente novamente');
    });
}