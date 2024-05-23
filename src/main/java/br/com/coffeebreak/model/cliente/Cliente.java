package br.com.coffeebreak.model.cliente;

import br.com.coffeebreak.model.pedido.Pedido;
import br.com.coffeebreak.service.constant.Mensagem;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "cliente")
public class Cliente implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    @NotEmpty(message = Mensagem.FORMULARIO_VALIDACAO_NOME)
    private String nome;

    @Column(nullable = false, unique = true)
    @Email(message = Mensagem.FORMULARIO_VALIDACAO_EMAIL)
    private String email;

    @Column(nullable = false)
    @NotEmpty(message = Mensagem.FORMULARIO_VALIDACAO_SENHA)
    private String senha;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = Mensagem.FORMULARIO_VALIDACAO_CPF)
    private String cpf;

    @Column(nullable = false)
    @NotEmpty(message = Mensagem.FORMULARIO_VALIDACAO_ENDERECO)
    private String endereco;

    @Column(nullable = false)
    @NotEmpty(message = Mensagem.FORMULARIO_VALIDACAO_TELEFONE)
    private String telefone;


    @Transient
    private String tipo = "CLIENTE";

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.tipo.equals("CLIENTE")) {
            return List.of(new SimpleGrantedAuthority("USER"));
        }

        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
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
