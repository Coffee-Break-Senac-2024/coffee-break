package br.com.coffeebreak.service.auth;

import br.com.coffeebreak.model.cliente.Cliente;
import br.com.coffeebreak.model.funcionario.Funcionario;
import br.com.coffeebreak.repositories.ClienteRepository;
import br.com.coffeebreak.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Cliente> cliente = clienteRepository.findByEmail(email);
        if (cliente.isPresent()) {
            return cliente.get();
        }

        Optional<Funcionario> funcionario = funcionarioRepository.findByEmail(email);
        if (funcionario.isPresent()) {
            return funcionario.get();
        }

        throw new UsernameNotFoundException("Usuário não encontrado.");
    }

}
