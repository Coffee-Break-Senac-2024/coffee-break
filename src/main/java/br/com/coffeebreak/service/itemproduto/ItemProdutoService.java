package br.com.coffeebreak.service.itemproduto;

import br.com.coffeebreak.model.ItemProduto.ItemProduto;
import br.com.coffeebreak.repositories.ItemProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemProdutoService {
    @Autowired
    private ItemProdutoRepository repository;

    public ItemProduto salvar(ItemProduto itemProduto) {
        return repository.save(itemProduto);
    }
}
