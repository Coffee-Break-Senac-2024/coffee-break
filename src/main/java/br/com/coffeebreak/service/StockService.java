package br.com.coffeebreak.service;

import br.com.coffeebreak.dto.EstoqueDTO;
import br.com.coffeebreak.model.estoque.Estoque;
import br.com.coffeebreak.repositories.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Autowired
    public EstoqueRepository repository;

    public EstoqueDTO insert(EstoqueDTO estoqueDTO) {
        Estoque estoque = new Estoque();
        copyDtoToEntity(estoqueDTO, estoque);
        estoque = repository.save(estoque);
        return new EstoqueDTO();
    }

    private void copyDtoToEntity(EstoqueDTO estoqueDTO, Estoque estoqueEntity) {
        estoqueEntity.setNome(estoqueDTO.getName());
        estoqueEntity.setQuantidade(estoqueDTO.getQuantity());
    }
}
