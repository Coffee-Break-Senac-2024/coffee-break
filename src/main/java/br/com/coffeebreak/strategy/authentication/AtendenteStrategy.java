package br.com.coffeebreak.strategy.authentication;

import br.com.coffeebreak.dto.TokenDTO;
import br.com.coffeebreak.enums.TipoFuncionario;
import br.com.coffeebreak.model.funcionario.Funcionario;
import br.com.coffeebreak.repositories.FuncionarioRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

@Service
public class AtendenteStrategy implements LoginStrategy {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Value("${atendente.security.token.secret}")
    private String atendenteKey;

    @Override
    public TokenDTO login(String email, String password) {

        Optional<Funcionario> funcionario = this.funcionarioRepository.findByEmail(email);

       boolean matches = funcionario.isPresent() && funcionario.get().getSenha().equals(password) && funcionario.get().getTipoFuncionario().equals(TipoFuncionario.ATENDENTE);

        if (!matches) {
            throw new RuntimeException("Email/senha erradas");
        }

        Algorithm algorithm = Algorithm.HMAC256(atendenteKey);
        String token = JWT.create().withIssuer("Atendente").withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(funcionario.get().getId().toString())
                .sign(algorithm);

        return new TokenDTO(token);
    }
}
