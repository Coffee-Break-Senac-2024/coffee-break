package br.com.coffeebreak.model.cliente;

import br.com.coffeebreak.model.pedido.Pedido;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cpf;
    @Column(nullable = false)
    private String endereco;
    @Column(nullable = false)
    private String telefone;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;
}
