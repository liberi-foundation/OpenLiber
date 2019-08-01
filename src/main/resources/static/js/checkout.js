function checkout(id, descricao, valor, quantidade, btn) {
    btn.attr('disabled', 'true').html('Carregando');
    
    var item = {
        "id": id,
        "descricao": descricao,
        "valor": valor,
        "quantidade": quantidade
    }

    $.ajax({
        url: ''
    });
}