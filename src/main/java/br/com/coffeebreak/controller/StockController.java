package br.com.coffeebreak.controller;

import br.com.coffeebreak.model.produto.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/administrator/stock")
public class StockController {

    @GetMapping
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("adm/stock/create");
        return mv;
    }

}
