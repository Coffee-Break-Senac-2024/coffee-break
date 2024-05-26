package br.com.coffeebreak.service.itemproduto;

import br.com.coffeebreak.model.ItemProduto.ItemProduto;
import br.com.coffeebreak.model.pedido.Pedido;
import br.com.coffeebreak.repositories.ItemProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemProdutoService {
    @Autowired
    private ItemProdutoRepository repository;

    public ItemProduto salvar(ItemProduto itemProduto) {
        return repository.save(itemProduto);
    }

    public void salvarItemProdutos(List<ItemProduto> lista, Pedido pedido){
        for(ItemProduto  item : pedido.getItemProdutos()){
            item.setPedido(pedido);
          repository.save(item);
        }
    }
}
