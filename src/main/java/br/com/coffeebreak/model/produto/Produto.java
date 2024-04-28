package br.com.coffeebreak.model.produto;

import br.com.coffeebreak.model.estoque.Estoque;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer quantidadeUtilizada;

    @Column(nullable = false)
    private Double preco;

    @OneToMany()
    @JoinColumn(name = "id_produto")
    private List<Estoque> estoqueList;

}
