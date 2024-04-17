package br.com.coffeebreak.model.pedido;

import br.com.coffeebreak.enums.TipoPedido;
import br.com.coffeebreak.model.ItemProduto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private TipoPedido tipoPedido;

    private Double precoTotal;

    private String situacao;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "pedido")
    private List<ItemProduto> itemProdutos;

}
