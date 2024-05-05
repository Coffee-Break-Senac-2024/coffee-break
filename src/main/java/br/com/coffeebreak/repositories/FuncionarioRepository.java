package br.com.coffeebreak.repositories;

import br.com.coffeebreak.model.funcionario.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, String> {
    Optional<Funcionario> findByEmail(String email);
    @Query(value = "SELECT u FROM funcionarios u WHERE u.nome LIKE %?1%")
    Page<Funcionario> findAllByNomeIgnoreCase(String nome, Pageable pageable);
}
