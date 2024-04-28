package br.com.coffeebreak.controller;

import br.com.coffeebreak.model.estoque.Estoque;
import br.com.coffeebreak.model.produto.Produto;
import br.com.coffeebreak.repositories.ProdutoRepository;
import br.com.coffeebreak.service.EstoqueService;
import br.com.coffeebreak.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/administrator/products")
public class ProductController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private EstoqueService estoqueService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("administrator/product/index");

        List<Produto> produtos = this.produtoService.getAllProducts();


        mv.addObject("produtos", produtos);
        return mv;
    }

    @GetMapping("/create")
    public ModelAndView create(){
        ModelAndView mv = new ModelAndView("administrator/product/create");
        mv.addObject("ingredientes", this.estoqueService.getAllIngredientes());
        mv.addObject("produto", new Produto());
        return mv;
    }

    @PostMapping("/create")
    public ModelAndView createProduct(@ModelAttribute("produto") Produto produto, @RequestParam(name = "ingredientes") List<String> ingredientes, RedirectAttributes redirectAttributes) {

       produto.setEstoqueList(getIngredientsToBeUsed(ingredientes));

        if (this.produtoService.cadastrarProduto(produto)) {
            redirectAttributes.addFlashAttribute("success", "Produto cadastrado com sucesso!");
            return new ModelAndView("redirect:/administrator/products");
        }

        redirectAttributes.addFlashAttribute("error", "Não foi possível cadastrar este produto.");
        return new ModelAndView("redirect:/administrator/products/create");
    }


    private List<Estoque> getIngredientsToBeUsed(List<String> ingredientes) {
        List<Estoque> list = new ArrayList<>();
        for (String idEstoque : ingredientes) {
            list.add(this.estoqueService.getIngredientById(idEstoque));
        }

        return list;
    }
}
