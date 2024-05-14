$(document).ready(function(){
    $('.deletar').on('click', function(){
        let id = $(this).data('id');
        let nome = $(this).data('nome');
        let caminhoURL = $(this).data('url');

        let url = caminhoURL + '/' +  id;

        $('#formularioExcluir').attr('action', url);

        $('#nome').text(nome);

        $('#modalExcluir').modal('show');
    });

    $('#btnConfirmarDelecao').on('click', function(){
        $('#formularioExcluir').submit();
    });
});