package br.com.coffeebreak.service.funcionario.strategy;


import br.com.coffeebreak.config.UsernamePasswordWrongException;
import br.com.coffeebreak.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@Component
@RequiredArgsConstructor
public class GerenteStrategy implements LoginStrategy {

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final AuthService authService;

    @Override
    public void login(String email, String password) {
        UserDetails funcionario = this.authService.loadUserByUsername(email);

        if (funcionario ==  null) {
            throw new UsernamePasswordWrongException("Email/Senha errados.");
        }

        System.out.println(password);
        System.out.println(funcionario.getPassword());

        boolean matches = passwordEncoder.matches(password, funcionario.getPassword());

        if (!matches) {
            throw new UsernamePasswordWrongException("Email/Senha errados.");
        }

        Authentication auth = new UsernamePasswordAuthenticationToken(email, password);

        Authentication authenticationAuth = authenticationManager.authenticate(auth);

        SecurityContextHolder.getContext().setAuthentication(authenticationAuth);


    }
}