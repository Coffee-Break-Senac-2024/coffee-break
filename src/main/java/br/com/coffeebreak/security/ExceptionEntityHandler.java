package br.com.coffeebreak.config;


import br.com.coffeebreak.model.cliente.exceptions.ClientAlreadyRegistered;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ExceptionEntityHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView handleAccessDenied(AccessDeniedException accessDeniedException) {
       ModelAndView mv = new ModelAndView("/administrator/error");
       mv.addObject("error", "Você não tem permissão para acessar essa área");
       return mv;
    }

    @ExceptionHandler(ClientAlreadyRegistered.class)
    public String handleClientAlreadyRegistered(ClientAlreadyRegistered clientAlreadyRegistered, RedirectAttributes redirectAttributes) {
        System.out.println("chegou aqui no client already");
        redirectAttributes.addFlashAttribute("errorCliente", clientAlreadyRegistered.getMessage());
        return "redirect:/cadastro";
    }

    @ExceptionHandler(UsernamePasswordWrongException.class)
    public String handleUsernamePasswordException(UsernamePasswordWrongException exception, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", exception.getMessage());
        return "redirect:/login";
    }
}
