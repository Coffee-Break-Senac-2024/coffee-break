$(document).ready(function(){
    $('.actionForm').on('click', function(){
        let id = $(this).data('id');
        let nome = $(this).data('nome');
        let caminhoURL = $(this).data('url');

        let url = caminhoURL + '/' +  id;

        $('#formulario').attr('action', url);

        $('#nome').text(nome);

        $('#modal').modal('show');
    });

    $('#btnConfirmar').on('click', function(){
        $('#formularioExcluir').submit();
    });
});