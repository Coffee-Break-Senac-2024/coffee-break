package br.com.coffeebreak.repositories;

import br.com.coffeebreak.model.pedido.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, String> {

    @Query("SELECT p FROM pedido p WHERE p.cliente.id = :clienteId AND p.situacao = :situacao")
    List<Pedido> getPedidosPorClienteStatus(String clienteId, String situacao);

    @Query("SELECT p FROM pedido p WHERE  p.situacao = :situacao")
    List<Pedido> getPedidosPorAndamento(String situacao);

    @Query(value= "SELECT p FROM pedido p where p.createdAt BETWEEN ?1 AND ?2")
    List<Pedido> getPedidosByTime(LocalDateTime inicio, LocalDateTime fim);

}
