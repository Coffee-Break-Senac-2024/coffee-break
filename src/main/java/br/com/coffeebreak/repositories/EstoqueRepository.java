package br.com.coffeebreak.repositories;

import br.com.coffeebreak.model.estoque.Estoque;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<Estoque, String> {
    Optional<Estoque>findByNomeIgnoreCase(String id);
    @Query(value = "SELECT nomes FROM estoque nomes WHERE nomes.nome LIKE %?1%")
    Page<Estoque> findAllByNomeIgnoreCase(String nome, Pageable pageable);
}
