package br.com.coffeebreak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adm")
public class AdmController {

    @RequestMapping
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
}
