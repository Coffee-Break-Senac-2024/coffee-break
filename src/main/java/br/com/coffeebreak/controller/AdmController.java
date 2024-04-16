package br.com.coffeebreak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AdmController {

    @RequestMapping
    public String home(){
        return "/coffeebreak/home";
    }

    @RequestMapping("/adm")
    public String index(){
        return "/adm/index";
    }

    @RequestMapping("/sobre")
    public String sobre(){
        return "/coffeebreak/sobre";
    }

<<<<<<< HEAD
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
=======
>>>>>>> f9671a6c01909ff0f4af3d70cc5a98c26206b169
}
