package br.com.coffeebreak.service.produto;

import br.com.coffeebreak.model.estoque.Estoque;
import br.com.coffeebreak.model.produto.Produto;
import br.com.coffeebreak.repositories.ProdutoRepository;
import br.com.coffeebreak.service.stock.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private final ProdutoRepository produtoRepository;

    @Autowired
    private final EstoqueService estoqueService;

    public ProdutoService(ProdutoRepository produtoRepository, EstoqueService estoqueService) {
        this.produtoRepository = produtoRepository;
        this.estoqueService = estoqueService;
    }

    public boolean cadastrarProduto(Produto produto) {
        try {
            this.produtoRepository.save(produto);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public void update(Produto produto) {
        if (produto.getId() == null) {
            throw new RuntimeException("Product nao encontrado.");
        }

        this.produtoRepository.save(produto);
    }

    public boolean excluirProduto(String idProduto) {
        try {
            this.produtoRepository.deleteById(idProduto);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Produto> getAllProducts() {
        return produtoRepository.findAll();
    }

    public Page<Produto> getAllProducts(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    public Page<Produto> getAllProducts(String nome, Pageable pageable) {
        return produtoRepository.findAllByNomeIgnoreCase(nome, pageable);
    }


    public Produto getProductById(String idProduto) {
        return this.produtoRepository.findById(idProduto).orElseThrow(() -> new RuntimeException("Não foi possível encontrar um produto com este id!"));
    }

    public List<Estoque> getIngredientsToBeUsed(List<String> ingredientes) {
        List<Estoque> list = new ArrayList<>();
        for (String idEstoque : ingredientes) {
            list.add(this.estoqueService.getIngredientById(idEstoque));
        }

        return list;
    }

}
