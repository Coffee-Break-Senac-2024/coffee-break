package br.com.coffeebreak.repositories;

import br.com.coffeebreak.model.ItemProduto.ItemProduto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemProdutoRepository extends JpaRepository<ItemProduto, String> {
}
