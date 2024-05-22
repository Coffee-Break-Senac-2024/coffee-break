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


    @Query(value = "SELECT pedido.itemProdutos FROM pedido pedido where pedido.createdAt BETWEEN ?1 AND ?2" )
    List<ItemProduto> getAllByPeriod(@Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);



}
