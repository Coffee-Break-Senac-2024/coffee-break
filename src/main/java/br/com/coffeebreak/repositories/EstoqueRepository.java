package br.com.coffeebreak.repositories;

import br.com.coffeebreak.model.estoque.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EstoqueRepository extends JpaRepository<Estoque, String> {

    Optional<Estoque> findById(String id);
}
