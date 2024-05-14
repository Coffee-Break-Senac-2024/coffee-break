package br.com.coffeebreak.repositories;

import br.com.coffeebreak.model.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

}
