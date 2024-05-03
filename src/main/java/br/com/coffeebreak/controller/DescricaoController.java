package br.com.coffeebreak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DescricaoController {

    @GetMapping("/descricao")
    public ModelAndView desc (){
        return new ModelAndView("coffeebreak/descricao");
    }
}
