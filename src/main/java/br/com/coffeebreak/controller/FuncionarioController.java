package br.com.coffeebreak.controller;

import br.com.coffeebreak.model.funcionario.Funcionario;
import br.com.coffeebreak.service.FuncionarioService;
import br.com.coffeebreak.service.constant.Messagem;
import br.com.coffeebreak.service.exception.EmailCadastradoException;
import br.com.coffeebreak.service.exception.FuncionarioIdNaoEncontradoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/administrator/employees")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @GetMapping
    public ModelAndView index(ModelAndView mv) {
        mv.setViewName("administrator/employee/index");
        List<Funcionario> funcionarios = service.getFuncionarios();
        mv.addObject("funcionarios", funcionarios);
        return mv;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView mv = new ModelAndView("administrator/employee/create");
        mv.addObject("funcionario", new Funcionario());
        mv.addObject("tiposFuncionario", service.tiposFuncionario());
        return mv;
    }

    @PostMapping("/create")
    public String create(
            @Valid @ModelAttribute("funcionario") Funcionario funcionario,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {

        model.addAttribute("tiposFuncionario", service.tiposFuncionario());

        if (bindingResult.hasErrors()) {
            return "administrator/employee/create";
        }

        try {
            service.insert(funcionario);
        } catch (EmailCadastradoException e) {
            bindingResult.rejectValue("email", e.getMessage(), e.getMessage());
            return "administrator/employee/create";
        }
        redirectAttributes.addFlashAttribute("success", Messagem.FUNCIOANARIO_CRIADO_SUCESSO);
        return "redirect:/administrator/employees";
    }

    @PostMapping("/delete/{id}")
    public ModelAndView delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
           ModelAndView mv = new ModelAndView("redirect:/administrator/employees");

           try {
                service.deletarPorId(id);
            } catch (FuncionarioIdNaoEncontradoException e) {
                redirectAttributes.addFlashAttribute("error", Messagem.FUNCIOANARIO_CRIADO_ERROR);
                return mv;
            }
        redirectAttributes.addFlashAttribute("success", Messagem.FUNCIOANARIO_DELETADO_SUCESSO);
        return mv;
    }


    @GetMapping("/update/{id}")
    public ModelAndView atualizarFuncionario(@PathVariable String id) {
        ModelAndView mv = new ModelAndView("administrator/employee/update");
        Funcionario funcionario = service.getFuncionarioPorID(id);
        mv.addObject("funcionario", funcionario);
        mv.addObject("tiposFuncionario", service.tiposFuncionario());
        return mv;
    }

    @PostMapping("/update/{id}")
    public String atualizarFuncionario(
            @Valid @ModelAttribute("funcionario") Funcionario funcionario,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {

        model.addAttribute("tiposFuncionario", service.tiposFuncionario());

        if (bindingResult.hasErrors()) {
            return "administrator/employee/update";
        }

        try {
            service.atualizarFuncionario(funcionario);
        } catch (FuncionarioIdNaoEncontradoException e) {
            bindingResult.rejectValue("id", e.getMessage(), e.getMessage());
        }
        redirectAttributes.addFlashAttribute("success", Messagem.FUNCIOANARIO_UPDATE_SUCESSO);
        return "redirect:/administrator/employees";
    }
}
