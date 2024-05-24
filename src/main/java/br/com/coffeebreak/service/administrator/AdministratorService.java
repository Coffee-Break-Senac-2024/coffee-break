package br.com.coffeebreak.service.administrator;

import br.com.coffeebreak.enums.SituacaoPedido;
import br.com.coffeebreak.enums.TipoPedido;
import br.com.coffeebreak.model.ItemProduto.ItemProduto;
import br.com.coffeebreak.model.pedido.Pedido;
import br.com.coffeebreak.model.produto.Produto;
import br.com.coffeebreak.service.itemproduto.ItemProdutoService;
import br.com.coffeebreak.service.pedido.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdministratorService {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ItemProdutoService itemProdutoService;

    private Pedido pedido;

    public void criarPedido(String nome, String tipo, List<Produto> produtos) {

        Pedido pedido = new Pedido();
        pedido.setNomeCliente(nome);
        pedido.setTipoPedido(TipoPedido.getTipoPedido(tipo));
        pedido.setPrecoTotal(calcularTotalCarrinho(produtos));
        pedido.setSituacao(SituacaoPedido.EM_ANDAMENTO.toString());
        pedido.setCreatedAt(LocalDateTime.now());

        List<ItemProduto> itensPedido = new ArrayList<>();
        for (Produto produto : produtos) {
            ItemProduto itemProduto = new ItemProduto();
            itemProduto.setPedido(pedido);
            itemProduto.setProduto(produto);
            itemProduto.setQuantidade(1);
            itemProduto.setPrecoProduto(produto.getPreco());
            itensPedido.add(itemProduto);
        }
        pedido.setItemProdutos(itensPedido);

        pedidoService.salvarPedido(pedido);
        salvarItemProdutos(pedido);
    }

    public double calcularTotalCarrinho(List<Produto> produtos) {
        double total = 0;
        if (produtos != null) {
            for (Produto item : produtos) {
                total += item.getPreco();
            }
        }

        return formatarValor(total);
    }

    private void salvarItemProdutos(Pedido pedido){
        for(ItemProduto  item : pedido.getItemProdutos()){
            item.setPedido(pedido);
            itemProdutoService.salvar(item);
        }
    }

    private double formatarValor(Double valor) {
        NumberFormat formatter = new DecimalFormat("#,###.00");
        String valorFormatado = formatter.format(valor);
        valorFormatado = valorFormatado.replace(",", ".");
        return Double.parseDouble(valorFormatado);
    }
}
