package br.com.coffeebreak.controller;

import br.com.coffeebreak.model.produto.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/administrator/products")
public class ProductController {

    @GetMapping
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("adm/product/index");

        List<Produto> produtos = new ArrayList<Produto>();

        produtos.add(new Produto("1", "Produto 1", 1, 12.00, null));
        produtos.add(new Produto("2", "Produto 2", 2, 15.00, null));
        produtos.add(new Produto("3", "Produto 3", 4, 18.00, null));
        produtos.add(new Produto("4", "Produto 4", 2, 20.00, null));


        mv.addObject("produtos", produtos);
        return mv;
    }

    @GetMapping("/create")
    public ModelAndView create(){
        ModelAndView mv = new ModelAndView("adm/product/create");
        return mv;
    }
}
