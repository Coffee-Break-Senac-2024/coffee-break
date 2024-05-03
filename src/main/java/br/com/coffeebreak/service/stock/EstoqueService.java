package br.com.coffeebreak.service.stock;

import br.com.coffeebreak.model.estoque.Estoque;
import br.com.coffeebreak.repositories.EstoqueRepository;
import br.com.coffeebreak.service.exception.NomeCadastradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository repository;

    @Transactional
    public void insert(Estoque estoque) {
        Optional<Estoque> estoqueOptinal = repository.findByNome(estoque.getNome());
        if (estoqueOptinal.isPresent()) {
           throw new NomeCadastradoException("Um Ingrediente já foi cadastrado com esse nome!.");
        }
        repository.save(estoque);
    }

    @Transactional(readOnly = true)
    public Page<Estoque> getIngredientesPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<Estoque> getAllIngredientes() {
        return repository.findAll();
    }

    @Transactional
    public Estoque getIngredientById(String id) {
        return repository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Não foi possivel encontrar um ingrediente com este id."));
    }

    @Transactional
    public void update(Estoque estoque) {
        if (estoque.getId() == null){
            throw new RuntimeException("Não foi possível atualizar.");
        }
        repository.save(estoque);
    }

    @Transactional
    public void delete(String id){
        if (!repository.existsById(id)){
            throw new IllegalArgumentException("Não foi possivel encontrar um ingrediente com este id.");
        }
        repository.deleteById(id);
    }
}
