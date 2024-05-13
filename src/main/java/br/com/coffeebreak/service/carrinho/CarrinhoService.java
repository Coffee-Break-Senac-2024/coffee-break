package br.com.coffeebreak.service.carrinho;

import br.com.coffeebreak.model.ItemProduto.ItemProduto;
import br.com.coffeebreak.model.pedido.Pedido;
import br.com.coffeebreak.model.produto.Produto;
import br.com.coffeebreak.service.produto.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarrinhoService {

    @Autowired
    ProdutoService produtoService;

    private Pedido pedido = new Pedido();
    private int quantidadeCarrinho = 1;
    private int items = 0;

    public void adicionarItem(String produtoId, int quantidade, double preco) {
        Produto produto = produtoService.getProductById(produtoId);

        if (pedido.getItemProdutos() == null) {
            pedido.setItemProdutos(new ArrayList<>());
        }

        if (!validarItemCarrinho(produto)){
            ItemProduto itemProduto = new ItemProduto(pedido, produto, quantidade, preco);
            itemProduto.setId(String.valueOf(quantidadeCarrinho++));

            pedido.getItemProdutos().add(itemProduto);
            itemProduto.setPedido(pedido);
        }
    }

    public void removerItem(int id) {
        ItemProduto itemProduto = getItemCarrinho(id);
        if (itemProduto != null) {
            pedido.getItemProdutos().remove(itemProduto);
        }
    }

    public List<ItemProduto> getItensCarrinho() {
        return pedido.getItemProdutos();
    }

    public double calcularTotalCarrinho() {
        double total = 0;
        if (pedido.getItemProdutos() != null) {
            for (ItemProduto item : pedido.getItemProdutos()) {
                total += item.getPrecoProduto() * item.getQuantidade();
            }
        }
        return total;
    }

    public int getQuantidadeCarrinho() {
        quantidadeCarrinho = 0;
        if (pedido.getItemProdutos() != null) {
            return pedido.getItemProdutos().size();
        }
        return  quantidadeCarrinho;
    }

    private boolean validarItemCarrinho(Produto produto){
        for (ItemProduto item : pedido.getItemProdutos()) {
            if (item.getProduto().getId().equals(produto.getId())) {
                item.setQuantidade(item.getQuantidade() + 1);
                return true;
            }
        }
        return false;
    }

    private ItemProduto getItemCarrinho(int id){
        for (ItemProduto item : pedido.getItemProdutos()) {
            if (item.getId().equals(String.valueOf(id))) {
               return item;
            }
        }
        return null;
    }


}
