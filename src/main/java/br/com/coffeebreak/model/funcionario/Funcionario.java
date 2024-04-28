package br.com.coffeebreak.model.funcionario;

import br.com.coffeebreak.enums.TipoFuncionario;
import br.com.coffeebreak.model.pedido.Pedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private TipoFuncionario tipoFuncionario;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private LocalDateTime entrada;


    private LocalDateTime saida;

    @OneToMany(mappedBy = "funcionario")
    List<Pedido> pedidos;

}
