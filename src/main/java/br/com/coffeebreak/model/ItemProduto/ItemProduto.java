package br.com.coffeebreak.model.ItemProduto;

import br.com.coffeebreak.model.pedido.Pedido;
import br.com.coffeebreak.model.produto.Produto;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "item_produto")
public class ItemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Integer quantidade;

    private Double precoProduto;

    @OneToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;
    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    public ItemProduto(Pedido pedido, Produto produto, Integer quantidade, Double precoProduto) {
        this.setPedido(pedido);
        this.setProduto(produto);
        this.quantidade = quantidade;
        this.precoProduto = precoProduto;
    }
}
