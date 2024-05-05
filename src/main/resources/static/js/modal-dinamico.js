$(document).ready(function(){
    $('.deletar').on('click', function(){
        let id = $(this).data('id');
        let nome = $(this).data('nome');
        let caminhoURL = $(this).data('url');

        // Cria a Url de deleção
        let url = caminhoURL + id;
        $('#formularioExcluir').attr('action', url);

        // Atualiza o nome do ingrediente no modal
        $('#nome').text(nome);

        // Exibe o modal de exclusão
        $('#modalExcluir').modal('show');
    });

    // Quando o botão de confirmar deleção for clicado
    $('#btnConfirmarDelecao').on('click', function(){
        // Envia o formulário
        $('#formularioExcluir').submit();
    });
});