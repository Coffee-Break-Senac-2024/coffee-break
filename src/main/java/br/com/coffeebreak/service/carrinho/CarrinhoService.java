package br.com.coffeebreak.service.carrinho;

import br.com.coffeebreak.enums.SituacaoPedido;
import br.com.coffeebreak.enums.TipoPedido;
import br.com.coffeebreak.model.ItemProduto.ItemProduto;
import br.com.coffeebreak.model.cliente.Cliente;
import br.com.coffeebreak.model.pedido.Pedido;
import br.com.coffeebreak.model.produto.Produto;
import br.com.coffeebreak.service.Helpers;
import br.com.coffeebreak.service.cliente.ClienteLogadoService;
import br.com.coffeebreak.service.itemproduto.ItemProdutoService;
import br.com.coffeebreak.service.pedido.PedidoService;
import br.com.coffeebreak.service.produto.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class CarrinhoService {

    @Autowired
    ProdutoService produtoService;

    @Autowired
    PedidoService pedidoService;

    @Autowired
    ItemProdutoService itemProdutoService;


    @Autowired
    private ClienteLogadoService clienteLogadoService;

    private Pedido pedido;
    private int quantidadeCarrinho = 1;

    public CarrinhoService (){
        this.pedido = new Pedido();
    }


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

        return Helpers.formatarValor(total);
    }

    public int getQuantidadeCarrinho() {
        quantidadeCarrinho = 0;
        if (pedido.getItemProdutos() != null) {
            return pedido.getItemProdutos().size();
        }
        return  quantidadeCarrinho;
    }

    public Pedido salvarPedido(){
       try {
           novoPedido();
       }catch (UsernameNotFoundException e){
           throw new UsernameNotFoundException("Usuário não encontrado");
       }
        return this.pedido = new Pedido();
    }

    public void novoPedido(){
        Cliente cliente = clienteLogadoService.getLoggedInCliente();
        pedido.setCliente(cliente);
        pedido.setNomeCliente(cliente.getNome());
        pedido.setCreatedAt(LocalDateTime.now());
        pedido.setTipoPedido(TipoPedido.ENTREGA);
        pedido.setSituacao(SituacaoPedido.EM_ANDAMENTO.toString());
        pedido.setFuncionario(null);
        pedido.setPrecoTotal(calcularTotalCarrinho());
        pedidoService.salvarPedido(pedido);
        itemProdutoService.salvarItemProdutos(pedido.getItemProdutos(), pedido);
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
