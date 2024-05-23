package br.com.coffeebreak.service.cliente;

import br.com.coffeebreak.model.cliente.Cliente;
import br.com.coffeebreak.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClienteLogadoService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente getLoggedInCliente() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            String email = ((UserDetails) authentication.getPrincipal()).getUsername();
            return clienteRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Cliente não encontrado."));
        }
        throw new UsernameNotFoundException("Usuário não está autenticado.");
    }
}
