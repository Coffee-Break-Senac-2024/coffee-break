package br.com.coffeebreak.controller;

import br.com.coffeebreak.dto.UserDTO;
import br.com.coffeebreak.model.produto.Produto;
import br.com.coffeebreak.service.produto.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller()
@RequestMapping("/")
public class CoffeeBreakController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public ModelAndView home(@PageableDefault(size = 8) Pageable pageable){
        ModelAndView mv = new ModelAndView("coffeebreak/home");
        Page<Produto> produtos = service.getAllProducts(pageable);
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
        ModelAndView mv = new ModelAndView("coffeebreak/login");
        mv.addObject("user", new UserDTO());
        return mv;
    }

    @GetMapping("/pedidos")
    public ModelAndView pedidos() {
        return new ModelAndView("coffeebreak/pedidos");
    }

    @GetMapping("/historicoPedidos")
    public ModelAndView historicoPedidos() {
        return new ModelAndView("coffeebreak/historicoPedidos");
    }

    @GetMapping("/descricao")
    public ModelAndView desc (){
        return new ModelAndView("coffeebreak/descricao");
    }

}
