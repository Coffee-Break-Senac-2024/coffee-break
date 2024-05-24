package br.com.coffeebreak.service.funcionario;

import br.com.coffeebreak.model.funcionario.Funcionario;
import br.com.coffeebreak.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioLogadoService {
    @Autowired
    private FuncionarioRepository funcionarioService;

    public Funcionario getLoggedInFuncionario() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            String email = ((UserDetails) authentication.getPrincipal()).getUsername();
            return funcionarioService.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Funcionário não encontrado."));
        }
        throw new UsernameNotFoundException("Usuário não está autenticado.");
    }
}
