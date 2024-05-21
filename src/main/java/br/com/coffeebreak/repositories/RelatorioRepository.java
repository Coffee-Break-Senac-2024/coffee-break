package br.com.coffeebreak.repositories;

import br.com.coffeebreak.model.ItemProduto.ItemProduto;
import br.com.coffeebreak.model.produto.Produto;
import br.com.coffeebreak.model.relatorio.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RelatorioRepository extends JpaRepository<Relatorio, String> {
@Query(value = "SELECT DISTINCT p.nome FROM produto p")
List<String> getAllProdutosNames();

@Query(value = "SELECT p FROM produto p WHERE p.nome = ?1")
Produto getByName(String nome);


    @Query(value = "SELECT itemProduto FROM item_produto itemProduto WHERE itemProduto.id = :id AND itemProduto.pedido.createdAt BETWEEN :inicio AND CURRENT_TIMESTAMP")
    List<ItemProduto> getAllByPeriod(@Param("id") String id, @Param("inicio") LocalDateTime inicio);






}
