package br.com.coffeebreak.controller.cliente;

import br.com.coffeebreak.model.cliente.Cliente;
import br.com.coffeebreak.service.cliente.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cadastro")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ModelAndView registerNewCliente(){
        ModelAndView  mv = new ModelAndView("coffeebreak/cadastro");
        mv.addObject("cliente", new Cliente());
        return mv;
    }
    @PostMapping("/create")
    public ModelAndView cadastrar(@Valid  @ModelAttribute("cliente") Cliente cliente,
                           BindingResult bindingResult, RedirectAttributes redirAttr){

        //Verifica se tem erros
        if(bindingResult.hasErrors())     {
            //tratativa de erro
            return new ModelAndView("redirect:/cadastro");
        }

        try{
            clienteService.insertCliente(cliente);
        } catch (Exception e){
            System.out.println(e);
          //  return new ModelAndView("redirect:/cadastro");
        }


        //AddFlashAttribute adiciona um atributo flash
        //Um atributo flash dura apenas duas requisições, permitindo a utilização do
        //Post-Redirect-Get que redireciona a aplicação após realizar o Post.
        redirAttr.addFlashAttribute("cliente",cliente);
        return new ModelAndView("redirect:/login");
    }



}
