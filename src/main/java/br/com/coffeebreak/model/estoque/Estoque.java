package br.com.coffeebreak.model.estoque;

import br.com.coffeebreak.model.produto.Produto;
import br.com.coffeebreak.service.constant.Mensagem;
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
    @NotEmpty(message = Mensagem.FORMULARIO_VALIDACAO_NOME)
    @Size(max = 20, message = Mensagem.FORMULARIO_VALIDACAO_LIMITE_CARACTER)
    private String nome;

    @Column(nullable = false)
    @NotNull(message = Mensagem.FORMULARIO_VALIDACAO_QUANTIDADE)
    @Min(value = 1, message = Mensagem.FORMULARIO_VALIDACAO_QUANTIDADE_MENOR)
    @Max(value = 100, message = Mensagem.FORMULARIO_VALIDACAO_QUANTIDADE_MAIOR)
    private Integer quantidade;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_produto", insertable = false, updatable = false)
    private Produto produto;
}
