package br.com.coffeebreak.controller.administrator;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administrator")
public class AdministratorController {

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('GERENTE') || hasAuthority('ATENDENTE')")
    public String index(){
        return "/administrator/index";
    }

}
