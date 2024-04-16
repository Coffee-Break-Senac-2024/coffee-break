package br.com.coffeebreak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AdmController {

    @RequestMapping("/adm")
    public String index(){
        return "/adm/index";
    }

    @RequestMapping("/sobre")
    public String sobre(){
        return "/coffeebreak/sobre";
    }

    @RequestMapping("/home")
    public String home(){
        return "/coffeebreak/home";
    }

    @GetMapping("/login")
    public String login() {
        return "/coffeebreak/login";
    }

    @GetMapping("/create")
    public String create() {return "/coffeebreak/cadastro";}

}
