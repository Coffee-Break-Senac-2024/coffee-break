package br.com.coffeebreak.repositories;

import br.com.coffeebreak.model.ItemProduto.ItemProduto;
import br.com.coffeebreak.model.pedido.Pedido;
import br.com.coffeebreak.model.relatorio.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RelatorioRepository extends JpaRepository<Relatorio, String> {



    @Query(value = "SELECT pedido.itemProdutos FROM pedido pedido where pedido.createdAt BETWEEN ?1 AND ?2" )
    List<ItemProduto> getAllByPeriod(@Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);

    @Query(value= "SELECT p FROM pedido p where p.createdAt BETWEEN ?1 AND ?2")
    List<Pedido> getPedidosByTime(LocalDateTime inicio, LocalDateTime fim);


}
