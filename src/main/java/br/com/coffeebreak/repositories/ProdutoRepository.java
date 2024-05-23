package br.com.coffeebreak.repositories;

import br.com.coffeebreak.model.produto.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdutoRepository extends JpaRepository<Produto, String> {

    @Query(value = "SELECT u FROM produto u WHERE u.nome LIKE %?1%")
    Page<Produto> findAllByNomeIgnoreCase(String nome, Pageable pageable);
}
