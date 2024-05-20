package br.com.coffeebreak.controller;

import br.com.coffeebreak.dto.UserDTO;
import br.com.coffeebreak.model.funcionario.Funcionario;
import br.com.coffeebreak.service.cliente.ClienteService;
import br.com.coffeebreak.service.auth.AuthService;
import br.com.coffeebreak.service.funcionario.FuncionarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private AuthService authService;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private ClienteService clienteService;

    private SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    @PostMapping("/")
    public String login(@ModelAttribute("user") UserDTO userDTO, HttpServletRequest request, HttpServletResponse response) {

        if (this.funcionarioService.getFuncionarioByEmail(userDTO.getEmail()) != null) {
            UserDetails userDetails = authService.loadUserByUsername(userDTO.getEmail());
            Funcionario funcionario = (Funcionario) userDetails;
            System.out.println(funcionario.getEmail());
            funcionarioService.authenticate(funcionario, userDTO.getSenha());

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            System.out.println(auth.getAuthorities());

            securityContextRepository.saveContext(SecurityContextHolder.getContext(), request, response);

            return "redirect:/administrator";
        }
        else {
           this.clienteService.login(userDTO.getEmail(), userDTO.getSenha());
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            System.out.println(auth.getAuthorities());

            securityContextRepository.saveContext(SecurityContextHolder.getContext(), request, response);

            return "redirect:/";
        }
    }
}
