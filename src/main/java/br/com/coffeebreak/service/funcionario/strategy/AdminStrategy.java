package br.com.coffeebreak.service.funcionario.strategy;


import br.com.coffeebreak.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class AdminStrategy implements LoginStrategy {

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("authenticationManager")
    private final AuthenticationManager authenticationManager;



    private final AuthService authService;

    @Override
    public void login(String email, String password) {
        UserDetails funcionario = this.authService.loadUserByUsername(email);

        if (funcionario ==  null) {
            throw new RuntimeException("Email/Senha errados.");
        }

        boolean matches = passwordEncoder.matches(password, funcionario.getPassword());

        if (!matches) {
            throw new RuntimeException("Email/Senha errados.");
        }

        Authentication auth = new UsernamePasswordAuthenticationToken(email, password);

        Authentication authenticationAuth = authenticationManager.authenticate(auth);

        SecurityContextHolder.getContext().setAuthentication(authenticationAuth);


    }
}
