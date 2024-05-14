package br.com.coffeebreak.service.funcionario.strategy;

import br.com.coffeebreak.enums.TipoFuncionario;
import br.com.coffeebreak.model.funcionario.Funcionario;
import br.com.coffeebreak.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtendenteStrategy implements LoginStrategy {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public void login(String email, String password) {

        Optional<Funcionario> funcionario = this.funcionarioRepository.findByEmail(email);

       boolean matches = funcionario.isPresent() && funcionario.get().getSenha().equals(password) && funcionario.get().getTipoFuncionario().equals(TipoFuncionario.ATENDENTE);

        if (!matches) {
            throw new RuntimeException("Email/senha erradas");
        }



    }
}
