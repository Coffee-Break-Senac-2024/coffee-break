package br.com.coffeebreak.controller.administrator;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

import br.com.coffeebreak.service.pedido.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/administrator")
public class AdministratorController {

    @Autowired
    private PedidoService service;
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('GERENTE') || hasAuthority('ATENDENTE')")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("/administrator/index");
        mv.addObject("pedidos", service.getPedidos());
        return mv;
    }

    @PostMapping("/finalizar-pedido")
    public ModelAndView finalizarPedido(@RequestParam("id") String id){
        System.out.println("ID: " + id);
        service.FinalizarPedido(id);
        return new ModelAndView("redirect:/administrator");

    }

}
