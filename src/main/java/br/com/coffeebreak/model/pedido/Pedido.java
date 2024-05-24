package br.com.coffeebreak.model.pedido;

import br.com.coffeebreak.enums.TipoPedido;
import br.com.coffeebreak.model.ItemProduto.ItemProduto;
import br.com.coffeebreak.model.cliente.Cliente;
import br.com.coffeebreak.model.funcionario.Funcionario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private TipoPedido tipoPedido;

    private Double precoTotal;

    private String situacao;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = true)
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private List<ItemProduto> itemProdutos;

    @ManyToOne
    @JoinColumn(name = "id_funcionario", nullable = true)
    private Funcionario funcionario;

}
