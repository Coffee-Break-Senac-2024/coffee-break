package br.com.coffeebreak.repositories;

import br.com.coffeebreak.model.funcionario.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, String> {
    Optional<Funcionario> findByEmail(String email);
}
