package br.com.coffeebreak.model.funcionario;

import br.com.coffeebreak.enums.TipoFuncionario;
import br.com.coffeebreak.model.pedido.Pedido;
import br.com.coffeebreak.service.constant.Mensagem;
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
    @NotEmpty(message = Mensagem.FORMULARIO_VALIDACAO_NOME)
    private String nome;

    @Column(nullable = false)
    @NotNull(message = Mensagem.FORMULARIO_VALIDACAO_TIPO_FUNCIONARIO)
    private TipoFuncionario tipoFuncionario;

    @Column(unique = true, nullable = false)
    @Email(message = Mensagem.FORMULARIO_VALIDACAO_EMAIL)
    private String email;

    @Column(nullable = false)
    @NotEmpty(message = Mensagem.FORMULARIO_VALIDACAO_SENHA)
    @Size(max = 14, min = 8, message = Mensagem.FORMULARIO_VALIDACAO_SENHA_TAMANHO)
    private String senha;

    @Column(nullable = false)
    @NotNull(message = Mensagem.FORMULARIO_VALIDACAO_DATA)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime entrada;


    private LocalDateTime saida;

    @OneToMany(mappedBy = "funcionario")
    List<Pedido> pedidos;

}
