package br.com.coffeebreak.produto;

import br.com.coffeebreak.model.estoque.Estoque;
import br.com.coffeebreak.model.produto.Produto;
import br.com.coffeebreak.repositories.ProdutoRepository;
import br.com.coffeebreak.service.produto.ProdutoService;
import br.com.coffeebreak.service.stock.EstoqueService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProdutoMock {
    @Mock
    private ProdutoRepository produtoRepository;
    @Mock
    private EstoqueService estoqueService;

    private ProdutoService produtoService;

    @Before()
    public void setUp() {
        produtoService = new ProdutoService(produtoRepository, estoqueService);
    }

    @Test
    public void testProductSave() {
        List<Estoque> estoqueList = new ArrayList<>();
        Estoque estoque = new Estoque();
        estoque.setNome("Cafe");
        estoque.setQuantidade(40);
        estoqueList.add(estoque);
        Produto produto = new Produto();
        produto.setNome("Café expresso");
        produto.setDescricao("Café expresso com açucar");
        produto.setPreco(14.0);
        produto.setNomeImagem("teste");
        produto.setEstoqueList(estoqueList);
        produto.setQuantidadeUtilizada(5);

        produtoRepository.save(produto);
        ArgumentCaptor<Produto> argumentCaptor = ArgumentCaptor.forClass(Produto.class);
        verify(produtoRepository).save(argumentCaptor.capture());
        Produto saveProduto = argumentCaptor.getValue();
        assertEquals(produto, saveProduto);
    }

    @Test
    public void testFindProductById() {

        Produto produto = new Produto();
        produto.setNome("Café expresso");
        produto.setDescricao("Café expresso com açúcar");
        produto.setPreco(14.0);
        produto.setNomeImagem("teste");
        produto.setQuantidadeUtilizada(5);


        when(produtoRepository.save(produto)).thenReturn(produto);


        Produto saved = produtoRepository.save(produto);


        when(produtoRepository.findById(saved.getId())).thenReturn(Optional.of(saved));


        Optional<Produto> found = produtoRepository.findById(saved.getId());


        assertTrue(found.isPresent());


        assertEquals(saved, found.get());
    }

}
