package br.com.coffeebreak.repositories;

import br.com.coffeebreak.enums.SituacaoPedido;
import br.com.coffeebreak.model.pedido.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, String> {

    @Query("SELECT p FROM pedido p WHERE p.cliente.id = :clienteId AND p.situacao = :situacao")
    List<Pedido> getPedidosPorClienteStatus(String clienteId, String situacao);

}
