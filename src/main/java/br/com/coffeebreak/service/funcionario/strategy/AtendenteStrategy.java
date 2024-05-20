package br.com.coffeebreak.service.funcionario.strategy;

import br.com.coffeebreak.enums.TipoFuncionario;
import br.com.coffeebreak.model.funcionario.Funcionario;
import br.com.coffeebreak.repositories.FuncionarioRepository;
import br.com.coffeebreak.service.funcionario.AuthFuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AtendenteStrategy implements LoginStrategy {

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final AuthFuncionarioService authFuncionarioService;

    @Override
    public void login(String email, String password) {
        UserDetails funcionario = this.authFuncionarioService.loadUserByUsername(email);

        if (funcionario ==  null) {
            throw new RuntimeException("Email/Senha errados.");
        }

        System.out.println(password);
        System.out.println(funcionario.getPassword());

        boolean matches = passwordEncoder.matches(password, funcionario.getPassword());

        if (!matches) {
            throw new RuntimeException("Email/Senha errados.");
        }

        Authentication auth = new UsernamePasswordAuthenticationToken(email, password);

        Authentication authenticationAuth = authenticationManager.authenticate(auth);

        SecurityContextHolder.getContext().setAuthentication(authenticationAuth);


    }
}
