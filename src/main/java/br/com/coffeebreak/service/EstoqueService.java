package br.com.coffeebreak.service;

import br.com.coffeebreak.dto.EstoqueDTO;
import br.com.coffeebreak.model.estoque.Estoque;
import br.com.coffeebreak.repositories.EstoqueRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository repository;

    @Transactional
    public boolean insert(EstoqueDTO estoqueDTO) {

        if (estoqueDTO == null){
            throw new IllegalArgumentException("O Ingrediente não pode ser nulo");
        }

        try {
            Estoque estoque = new Estoque();
            copyDtoToEntity(estoqueDTO, estoque);
            estoque = repository.save(estoque);

            return true;
        } catch (Exception e){
            e.printStackTrace();
           return false;
        }
    }

    public List<Estoque> getAllIngredientes() {
        return this.repository.findAll();
    }

    public Estoque getIngredientById(String estoqueId) {
        return this.repository.findById(estoqueId).orElseThrow(() -> new RuntimeException("Não foi possivel encontrar um ingrediente com este id"));
    }

    private void copyDtoToEntity(EstoqueDTO estoqueDTO, Estoque estoqueEntity) {
        estoqueEntity.setNome(estoqueDTO.getName());
        estoqueEntity.setQuantidade(estoqueDTO.getQuantity());
    }
}
