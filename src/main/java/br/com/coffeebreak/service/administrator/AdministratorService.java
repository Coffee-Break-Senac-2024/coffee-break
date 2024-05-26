package br.com.coffeebreak.service.administrator;

import br.com.coffeebreak.enums.SituacaoPedido;
import br.com.coffeebreak.enums.TipoPedido;
import br.com.coffeebreak.model.ItemProduto.ItemProduto;
import br.com.coffeebreak.model.funcionario.Funcionario;
import br.com.coffeebreak.model.pedido.Pedido;
import br.com.coffeebreak.model.produto.Produto;
import br.com.coffeebreak.service.Helpers;
import br.com.coffeebreak.service.funcionario.FuncionarioLogadoService;
import br.com.coffeebreak.service.itemproduto.ItemProdutoService;
import br.com.coffeebreak.service.pedido.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdministratorService {

    private final PedidoService pedidoService;
    private final ItemProdutoService itemProdutoService;
    private final FuncionarioLogadoService funcionarioLgdService;

    public void criarPedido(String nome, String tipo, List<Produto> produtos) {

        Pedido pedido = new Pedido();
        pedido.setNomeCliente(nome);
        pedido.setTipoPedido(TipoPedido.getTipoPedido(tipo));
        pedido.setPrecoTotal(calcularTotalCarrinho(produtos));
        pedido.setSituacao(SituacaoPedido.EM_ANDAMENTO.toString());
        pedido.setCreatedAt(LocalDateTime.now());

        Funcionario funcionario = funcionarioLgdService.getLoggedInFuncionario();
        pedido.setFuncionario(funcionario);

        List<ItemProduto> itensPedido = new ArrayList<>();
        setItemProduto(produtos, pedido, itensPedido);
        pedido.setItemProdutos(itensPedido);

        pedidoService.salvarPedido(pedido);
        itemProdutoService.salvarItemProdutos(itensPedido, pedido);
    }

    public double calcularTotalCarrinho(List<Produto> produtos) {
        double total = 0;
        if (produtos != null) {
            for (Produto item : produtos) {
                total += item.getPreco();
            }
        }

        return Helpers.formatarValor(total);
    }

    private static void setItemProduto(List<Produto> produtos, Pedido pedido, List<ItemProduto> itensPedido) {
        for (Produto produto : produtos) {
            ItemProduto itemProduto = new ItemProduto();
            itemProduto.setPedido(pedido);
            itemProduto.setProduto(produto);
            itemProduto.setQuantidade(1);
            itemProduto.setPrecoProduto(produto.getPreco());
            itensPedido.add(itemProduto);
        }
    }
}
