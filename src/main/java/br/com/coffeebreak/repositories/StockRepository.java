package br.com.coffeebreak.repositories;

import br.com.coffeebreak.model.estoque.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Estoque, String> {

}
