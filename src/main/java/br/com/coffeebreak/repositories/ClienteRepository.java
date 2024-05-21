package br.com.coffeebreak.repositories;

import br.com.coffeebreak.model.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface ClienteRepository extends JpaRepository<Cliente,String> {

    @Query(value="SELECT c FROM cliente c WHERE c.cpf = ?1")
    Optional<Cliente> findByCpf(String cpf);

    Optional<Cliente> findByEmail(String email);

    Optional<Cliente> findByCpfOrEmailOrTelefone(String cpf, String email, String telefone);


}
