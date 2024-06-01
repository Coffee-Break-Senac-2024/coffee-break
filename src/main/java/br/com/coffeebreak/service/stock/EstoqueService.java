package br.com.coffeebreak.service.stock;

import br.com.coffeebreak.model.ItemProduto.ItemProduto;
import br.com.coffeebreak.model.estoque.Estoque;
import br.com.coffeebreak.model.pedido.Pedido;
import br.com.coffeebreak.model.produto.Produto;
import br.com.coffeebreak.repositories.EstoqueRepository;
import br.com.coffeebreak.service.exception.NomeCadastradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {

    @Autowired
    private final EstoqueRepository repository;

    public EstoqueService(EstoqueRepository repository) {
        this.repository = repository;
    }

    /**
     * Cadastra um Ingrediente no banco de dados
     * @Return void
     */
    @Transactional
    public void insert(Estoque estoque) {
        Optional<Estoque> estoqueOptinal = repository.findByNomeIgnoreCase(estoque.getNome());
        if (estoqueOptinal.isPresent()) {
           throw new NomeCadastradoException("Um Ingrediente já foi cadastrado com esse nome!.");
        }
        repository.save(estoque);
    }

    /**
     * Retorna um Pageable de Estoque
     * @Return Pageable Funcionario
     */
    @Transactional(readOnly = true)
    public Page<Estoque> getIngredientesPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    /**
     * Retorna um Pageable de Estoque por nome
     * @Return Pageable Estoque
     */
    public Page<Estoque> getIngredientesPage(String nome, Pageable pageable) {
        return repository.findAllByNomeIgnoreCase(nome, pageable);
    }

    /**
     * Retorna uma lista de Estoque
     * @Return List Estoque
     */
    @Transactional(readOnly = true)
    public List<Estoque> getAllIngredientes() {
        return repository.findAll();
    }

    /**
     * Retorna um Estoque por ID
     * @Return Estoque
     */
    @Transactional
    public Estoque getIngredientById(String id) {
        return repository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Não foi possivel encontrar um ingrediente com este id."));
    }
    /**
     * Atualiza um Estoque por ID
     * @Return void
     */
    @Transactional
    public void update(Estoque estoque) {
        repository
                .findById(estoque.getId())
                .ifPresent(estoqueUpdate -> {
                    estoqueUpdate.setNome(estoque.getNome());
                    estoqueUpdate.setQuantidade(estoque.getQuantidade());
                    repository.save(estoqueUpdate);
                });
    }

    /**
     * Deleta um Estoque por ID
     * @Return void
     */
    @Transactional
    public void delete(String id){
        if (!repository.existsById(id)){
            throw new IllegalArgumentException("Não foi possivel encontrar um ingrediente com este id.");
        }
        repository.deleteById(id);
    }

    /**
     * Atualiza quantidade do Estoque por Pedido
     * @Return void
     */
    @Transactional
    public void atualizarQuantidadeEstoque(Pedido pedido) {
        for (ItemProduto produto : pedido.getItemProdutos()) {
            if (produto.getProduto() != null && produto.getProduto().getEstoqueList() != null) {
                List<Estoque> estoqueList = new ArrayList<>(produto.getProduto().getEstoqueList());
                for (Estoque ingrediente : estoqueList) {
                    Optional<Estoque> estoqueOptional = repository.findById(ingrediente.getId());

                    if (estoqueOptional.isPresent()) {
                        Estoque estoqueUpdate = estoqueOptional.get();
                        estoqueUpdate.setQuantidade(ingrediente.getQuantidade() - produto.getProduto().getQuantidadeUtilizada());

                        repository.save(estoqueUpdate);
                    }
                }
            }
        }
    }
}
