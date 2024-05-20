package br.com.coffeebreak.initializr;

import br.com.coffeebreak.enums.TipoFuncionario;
import br.com.coffeebreak.model.funcionario.Funcionario;
import br.com.coffeebreak.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AdminUserInitializr implements ApplicationListener {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        if (!funcionarioRepository.existsByTipoFuncionario(TipoFuncionario.ADMIN)) {
            Funcionario admin = new Funcionario();
            admin.setNome("Admin");
            admin.setEmail("admin@gmail.com");
            admin.setTipoFuncionario(TipoFuncionario.ADMIN);
            admin.setSenha(passwordEncoder.encode("admin@101921"));
            admin.setEntrada(LocalDateTime.now());

            funcionarioRepository.save(admin);
        }

    }
}
