function exibirMensagem(mensagem) {
    var mensagemElemento = $("#mensagem");
    mensagemElemento.text(mensagem);
    mensagemElemento.fadeIn();

    setTimeout(function () {
        mensagemElemento.fadeOut();
    }, 3000);
}

$(document).ready(function () {
    $(".formulario-enviar").click(function (event) {
        event.preventDefault();

        let form = $(this).closest("form");

        $.ajax({
            type: form.attr("method"),
            url: form.attr("action"),
            data: form.serialize(),
            success: function (response) {
                console.log("Item adicionado ao carrinho com sucesso!");
                exibirMensagem("Item adicionado ao carrinho com sucesso!");
            },
            error: function (xhr, status, error) {
                console.error("Ocorreu um erro ao adicionar o item ao carrinho:", error);
            }
        });
    });
});
