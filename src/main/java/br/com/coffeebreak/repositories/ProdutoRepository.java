package br.com.coffeebreak.repositories;

import br.com.coffeebreak.model.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
}
