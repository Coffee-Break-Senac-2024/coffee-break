package br.com.coffeebreak.repositories;

import br.com.coffeebreak.model.funcionario.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FuncionarioRepository extends JpaRepository<Funcionario, String> {
    @Query("SELECT COUNT(f) > 0 FROM funcionarios f WHERE f.email = :email")
    boolean existsByEmail(String email);
}
