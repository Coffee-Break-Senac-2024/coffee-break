package br.com.coffeebreak.stock;

import br.com.coffeebreak.model.estoque.Estoque;
import br.com.coffeebreak.repositories.EstoqueRepository;
import br.com.coffeebreak.service.stock.EstoqueService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StockMock {
    @Mock
    private EstoqueRepository estoqueRepository;
    private EstoqueService estoqueService;

    @Before
    public void setUp() {
        estoqueService = new EstoqueService(estoqueRepository);
    }

    @Test
    public void testSave() {
        Estoque estoque = new Estoque();
        estoque.setNome("Café");
        estoque.setQuantidade(50);

        estoqueRepository.save(estoque);

        ArgumentCaptor<Estoque> estoqueArgumentCaptor = ArgumentCaptor.forClass(Estoque.class);
        verify(estoqueRepository).save(estoqueArgumentCaptor.capture());
        Estoque saveEstoque = estoqueArgumentCaptor.getValue();
        assertEquals(estoque, saveEstoque);
    }


}
