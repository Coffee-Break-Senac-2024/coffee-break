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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "funcionarios")
public class Funcionario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    @NotEmpty(message = Mensagem.FORMULARIO_VALIDACAO_NOME)
    private String nome;

    @Column(nullable = false)
    @NotNull(message = "O campo tipo de funcinário é obrigatótio.")
    @Enumerated(EnumType.STRING)
    @NotNull(message = Mensagem.FORMULARIO_VALIDACAO_TIPO_FUNCIONARIO)
    private TipoFuncionario tipoFuncionario;

    @Column(unique = true, nullable = false)
    @Email(message = Mensagem.FORMULARIO_VALIDACAO_EMAIL)
    private String email;

    @Column(nullable = false)
    @NotEmpty(message = Mensagem.FORMULARIO_VALIDACAO_SENHA)
    private String senha;

    @Column(nullable = false)
    @NotNull(message = Mensagem.FORMULARIO_VALIDACAO_DATA)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime entrada;


    private LocalDateTime saida;

    @OneToMany(mappedBy = "funcionario")
    List<Pedido> pedidos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.tipoFuncionario.equals(TipoFuncionario.ADMIN)) {
            return List.of(new SimpleGrantedAuthority("ADMIN"));
        } else if (this.tipoFuncionario.equals(TipoFuncionario.ATENDENTE)) {
            return List.of(new SimpleGrantedAuthority("ATENDENTE"));
        }
        else {
            return List.of(new SimpleGrantedAuthority("GERENTE"));
        }
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
