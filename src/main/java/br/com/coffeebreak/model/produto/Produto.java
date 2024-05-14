package br.com.coffeebreak.model.produto;

import br.com.coffeebreak.model.estoque.Estoque;
import br.com.coffeebreak.service.constant.Mensagem;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    @NotEmpty(message = Mensagem.FORMULARIO_VALIDACAO_NOME)
    private String nome;

    @Column(nullable = false)
    @NotEmpty(message = Mensagem.FORMULARIO_VALIDACAO_DESCRICAO)
    private String descricao;

    @Column(nullable = false)
    @NotNull(message = Mensagem.FORMULARIO_VALIDACAO_QUANTIDADE)
    @Min(value = 1, message = Mensagem.FORMULARIO_VALIDACAO_QUANTIDADE_MAIOR)
    @Max(value = 100, message = Mensagem.FORMULARIO_VALIDACAO_QUANTIDADE_MENOR)
    private Integer quantidadeUtilizada;

    @Column(nullable = false)
    @NotEmpty(message = Mensagem.FORMULARIO_VALIDACAO_PRICE)
    private Double preco;

    @Column(nullable = false)
    @NotEmpty(message = Mensagem.FORMULARIO_VALIDACAO_IMAGEM)
    private String nomeImagem;

    @OneToMany()
    @JoinColumn(name = "id_produto")
    private List<Estoque> estoqueList;

}
