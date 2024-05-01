package br.com.coffeebreak.model.estoque;

import br.com.coffeebreak.model.produto.Produto;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "estoque")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    @NotEmpty(message = "O nome é obrigatório.")
    @Size(max = 20, message = "O limite de caracteres é 20.")
    private String nome;

    @Column(nullable = false)
    @NotNull(message = "A quantidade é obrigatória.")
    @Min(value = 1, message = "A quantidade deve ser maior que zero.")
    @Max(value = 100, message = "A quantidade deve ser menor que 100.")
    private Integer quantidade;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_produto", insertable = false, updatable = false)
    private Produto produto;
}
