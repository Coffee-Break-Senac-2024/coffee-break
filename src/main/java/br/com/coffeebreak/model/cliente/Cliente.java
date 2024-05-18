package br.com.coffeebreak.model.cliente;

import br.com.coffeebreak.model.pedido.Pedido;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    @NotBlank
    private String nome;

    @Column(nullable = false)
    @Email
    @NotBlank
    private String email;

    @Column(nullable = false)
    @NotBlank
    private String senha;

    @Column(nullable = false)
    @NotBlank
    private String cpf;
    @Column(nullable = false)
    @NotBlank
    private String endereco;
    @Column(nullable = false)
    @NotBlank
    private String telefone;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;
}
