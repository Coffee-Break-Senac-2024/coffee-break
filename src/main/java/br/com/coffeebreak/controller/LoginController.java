package br.com.coffeebreak.controller;

import br.com.coffeebreak.dto.UserDTO;
import br.com.coffeebreak.model.funcionario.Funcionario;
import br.com.coffeebreak.service.funcionario.AuthFuncionarioService;
import br.com.coffeebreak.service.funcionario.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private AuthFuncionarioService authFuncionarioService;

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping("/")
    public String login(@ModelAttribute("user") UserDTO userDTO) {

        if (this.funcionarioService.getFuncionarioByEmail(userDTO.getEmail()) != null) {
            UserDetails userDetails = authFuncionarioService.loadUserByUsername(userDTO.getEmail());
            Funcionario funcionario = (Funcionario) userDetails;
            System.out.println(funcionario.getEmail());
            funcionarioService.authenticate(funcionario, userDTO.getSenha());

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            System.out.println(auth.isAuthenticated());
            System.out.println(auth.getAuthorities());

            return "redirect:/administrator";
        }
        else {
            return "redirect:/login";
        }
    }
}
