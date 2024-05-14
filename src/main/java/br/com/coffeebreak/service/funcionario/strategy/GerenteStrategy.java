package br.com.coffeebreak.service.funcionario.strategy;



import br.com.coffeebreak.model.funcionario.Funcionario;
import br.com.coffeebreak.repositories.FuncionarioRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class GerenteStrategy implements LoginStrategy {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void login(String email, String password) {

        Optional<Funcionario> funcionario = this.funcionarioRepository.findByEmail(email);

        if (funcionario.isEmpty()) {
            throw new RuntimeException("Email/Senha errados.");
        }

        boolean matches = passwordEncoder.matches(password, funcionario.get().getSenha());

        if (!matches) {
            throw new RuntimeException("Email/Senha errados.");
        }

        // Cria a autenticacao
        Authentication auth = new UsernamePasswordAuthenticationToken(email, password);

        Authentication authenticationAuth = authenticationManager.authenticate(auth);

        SecurityContextHolder.getContext().setAuthentication(authenticationAuth);
    }
}
