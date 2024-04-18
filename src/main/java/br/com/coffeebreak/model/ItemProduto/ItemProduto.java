package br.com.coffeebreak.model.ItemProduto;

import br.com.coffeebreak.model.cliente.Cliente;
import br.com.coffeebreak.model.pedido.Pedido;
import br.com.coffeebreak.model.produto.Produto;
import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "item_produto")
public class ItemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Integer quantidade;

    private Double precoProduto;

    @OneToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;
    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

}
