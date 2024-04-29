package br.com.coffeebreak.model.funcionario;

import br.com.coffeebreak.enums.TipoFuncionario;
import br.com.coffeebreak.model.pedido.Pedido;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    @NotEmpty(message = "O campo nome é obrigatótio.")
    private String nome;

    @Column(nullable = false)
    @NotNull(message = "O campo tipo de funcinário é obrigatótio.")
    private TipoFuncionario tipoFuncionario;

    @Column(unique = true, nullable = false)
    @Email(message = "O campo email é obrigatótio.")
    private String email;

    @Column(nullable = false)
    @NotEmpty(message = "O campo nome é obrigatótio")
    @Size(max = 14, min = 8, message = "O campo senha dever ter no minímo 8 e maxímo 14 caracteres.")
    private String senha;

    @Column(nullable = false)
    @NotNull(message = "A data não pode ser nula.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime entrada;


    private LocalDateTime saida;

    @OneToMany(mappedBy = "funcionario")
    List<Pedido> pedidos;

}
