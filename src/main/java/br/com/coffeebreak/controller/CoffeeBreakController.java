package br.com.coffeebreak.controller;

import br.com.coffeebreak.controller.produto.ProductController;
import br.com.coffeebreak.model.produto.Produto;
import br.com.coffeebreak.service.produto.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller()
@RequestMapping("/")
public class CoffeeBreakController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("coffeebreak/home");
        List<Produto> produtos = service.getAllProducts();
        mv.addObject("produtos", produtos);
        return mv;
    }

    @GetMapping("/sobre")
    public ModelAndView sobre(){
        ModelAndView mv = new ModelAndView("coffeebreak/sobre");
        return mv;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("coffeebreak/login");
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("coffeebreak/cadastro");
    }

    @GetMapping("/pedidos")
    public ModelAndView pedidos() {
        return new ModelAndView("coffeebreak/pedidos");
    }

    @GetMapping("/historicoPedidos")
    public ModelAndView historicoPedidos() {
        return new ModelAndView("coffeebreak/historicoPedidos");
    }

}
