package br.com.coffeebreak.repositories;

import br.com.coffeebreak.model.pedido.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, String> {

    @Query("SELECT situacao FROM pedido situacao WHERE situacao.situacao = 'EM_ANDAMENTO'")
    List<Pedido> getPedidos();
}
