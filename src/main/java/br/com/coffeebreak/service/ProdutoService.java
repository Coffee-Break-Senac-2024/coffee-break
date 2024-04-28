package br.com.coffeebreak.service;

import br.com.coffeebreak.model.produto.Produto;
import br.com.coffeebreak.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public boolean cadastrarProduto(Produto produto) {
        try {

            this.produtoRepository.save(produto);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public List<Produto> getAllProducts() {
        return this.produtoRepository.findAll();
    }

    public boolean atualizarProduto(String idProduto, Produto newProduct) {
        Produto produto = getProductById(idProduto);
        produto.setNome(newProduct.getNome());
        produto.setPreco(newProduct.getPreco());
        produto.setQuantidadeUtilizada(newProduct.getQuantidadeUtilizada());
        produto.setEstoqueList(produto.getEstoqueList());
        this.produtoRepository.save(produto);
        return true;
    }

    public Produto getProductById(String idProduto) {
        return this.produtoRepository.findById(idProduto).orElseThrow(() -> new RuntimeException("Não foi possível encontrar um produto com este id!"));
    }

}
