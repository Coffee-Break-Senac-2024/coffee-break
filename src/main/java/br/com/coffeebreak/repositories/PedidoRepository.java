package br.com.coffeebreak.repositories;

import br.com.coffeebreak.model.pedido.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, String> {
}
