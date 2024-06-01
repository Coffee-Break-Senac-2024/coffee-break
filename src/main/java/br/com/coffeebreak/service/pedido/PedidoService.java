package br.com.coffeebreak.service.pedido;

import br.com.coffeebreak.enums.SituacaoPedido;
import br.com.coffeebreak.model.pedido.Pedido;
import br.com.coffeebreak.repositories.PedidoRepository;
import br.com.coffeebreak.service.stock.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repository;
    @Autowired
    private EstoqueService estoqueService;


    @Transactional
    public Pedido salvarPedido(Pedido pedido){
      return repository.save(pedido);
    }

    @Transactional
    public List<Pedido> getPedidos(){
        return repository.getPedidosPorAndamento(SituacaoPedido.EM_ANDAMENTO.toString());
    }

    @Transactional
    public void FinalizarPedido(String id) {
        repository
                .findById(id)
                .ifPresent(pedidoUpdate -> {
                    pedidoUpdate.setSituacao(SituacaoPedido.FINALIZADO.toString());

                    repository.save(pedidoUpdate);
                    estoqueService.atualizarQuantidadeEstoque(pedidoUpdate);
                });
    }
}
