package br.com.coffeebreak.model.relatorio;

import br.com.coffeebreak.enums.TipoFuncionario;
import br.com.coffeebreak.enums.TipoRelatorio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "relatorio")
public class Relatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private TipoRelatorio tipoRelatorio;
    @Column(nullable = false)
    private Double preco;
    @Column(nullable = false)
    private Integer qtdPedidosRealizados;
}
