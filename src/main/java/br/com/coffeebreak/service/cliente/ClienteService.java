package br.com.coffeebreak.service.cliente;

import br.com.coffeebreak.model.cliente.Cliente;
import br.com.coffeebreak.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class ClienteService{
    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public void insertCliente(Cliente cliente){
       Optional<Cliente> clienteOP = clienteRepository.findByCpf(cliente.getCpf());
       if(clienteOP.isPresent()){
           System.out.println("CLiente já cadastrado");
               return;
       }
       if(clienteOP.isEmpty()){
            System.out.println("Não pode ser nulo");
            return;
        }
       clienteRepository.save(cliente);

    }






}
