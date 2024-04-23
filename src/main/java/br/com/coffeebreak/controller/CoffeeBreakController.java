package br.com.coffeebreak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller()
@RequestMapping("/")
public class CoffeeBreakController {

    @GetMapping
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("coffeebreak/home");
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

}
