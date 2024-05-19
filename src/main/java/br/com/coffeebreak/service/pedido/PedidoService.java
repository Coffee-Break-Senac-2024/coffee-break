package br.com.coffeebreak.service.pedido;

import br.com.coffeebreak.model.pedido.Pedido;
import br.com.coffeebreak.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repository;

    @Transactional
    public Pedido salvarPedido(Pedido pedido){
      return repository.save(pedido);
    }
}
