package br.com.coffeebreak.controller;

import br.com.coffeebreak.model.cliente.Cliente;
import br.com.coffeebreak.repositories.ClienteRepository;
import br.com.coffeebreak.service.cliente.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import jakarta.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    /*@GetMapping("/cadastro")
    public ModelAndView create() {
        return new ModelAndView("coffeebreak/cadastro");
    }*/

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
