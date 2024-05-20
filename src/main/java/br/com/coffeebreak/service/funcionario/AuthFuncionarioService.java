package br.com.coffeebreak.service.funcionario;

import br.com.coffeebreak.model.funcionario.Funcionario;
import br.com.coffeebreak.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthFuncionarioService implements UserDetailsService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Funcionario> funcionario = funcionarioRepository.findByEmail(email);
        if (funcionario.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado.");
        }
        return funcionario.get();
    }

}
