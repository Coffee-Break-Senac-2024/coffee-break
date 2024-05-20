package br.com.coffeebreak.config;


import br.com.coffeebreak.model.cliente.exceptions.ClientAlreadyRegistered;
import br.com.coffeebreak.model.funcionario.exceptions.AcessDeniedException;
import br.com.coffeebreak.service.exception.EmailCadastradoException;
import org.springframework.expression.AccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionEntityHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView handleAccessDenied(AccessDeniedException accessDeniedException) {
       ModelAndView mv = new ModelAndView("/administrator/error");
       mv.addObject("error", "Você não tem permissão para acessar essa área");
       return mv;
    }

    @ExceptionHandler(ClientAlreadyRegistered.class)
    public ModelMap handleClientAlreadyRegistered(ClientAlreadyRegistered clientAlreadyRegistered) {
        System.out.println("caiu aqui");
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("errorCliente", clientAlreadyRegistered.getMessage());
        return modelMap;
    }
}
