package br.com.coffeebreak.controller;

import br.com.coffeebreak.dto.FuncionarioDTO;
import br.com.coffeebreak.enums.TipoFuncionario;
import br.com.coffeebreak.model.funcionario.Funcionario;
import br.com.coffeebreak.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/administrator/employees")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @GetMapping
    public ModelAndView index(ModelAndView mv){
       mv.setViewName("administrator/employee/index");
       List<Funcionario> funcionarios = service.getFuncionarios();
        mv.addObject("funcionarios",funcionarios);
        return mv;
    }

    @GetMapping("/create")
    public ModelAndView create(){
        ModelAndView mv = new ModelAndView("administrator/employee/create");
        mv.addObject("funcionario", new FuncionarioDTO());
        mv.addObject("tiposFuncionario", tiposFuncionario());
        return mv;
    }

    @PostMapping("/create")
    public String create(
            @Valid @ModelAttribute("funcionario") FuncionarioDTO funcionarioDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model){

        model.addAttribute("tiposFuncionario", tiposFuncionario());

        if (bindingResult.hasErrors()){
            return "administrator/employee/create";
        }

        boolean saved = service.insert(funcionarioDTO);

        if (saved){
            redirectAttributes.addFlashAttribute("success", "Funcionário cadastrado com sucesso!");
            return "redirect:/administrator/employees";
        }

        model.addAttribute("error", "Email já cadastrado");
        return "administrator/employee/create";
    }

    @PostMapping("/delete/{id}")
    public String delete(@RequestParam String id, RedirectAttributes redirectAttributes){
        boolean saved = service.deletarPorId(id);

        if (saved){
            redirectAttributes.addFlashAttribute("success", "Funcionário deletado com sucesso!");
            return "redirect:/administrator/employees";
        }
        redirectAttributes.addFlashAttribute("error", "Error ao tentar deletar funcionário");
        return "redirect:/administrator/employees";
    }

    @GetMapping("/update/{id}")
    public String atualizarFuncionario(
            @PathVariable String id,
            Model model){

        FuncionarioDTO funcionario = service.getFuncionarioPorID(id);
        model.addAttribute("funcionario", funcionario);
        return "administrator/employee/update";
    }

    @PostMapping("/update/{id}")
    public String atualizarFuncionario(
            @PathVariable String id,
           @Valid @ModelAttribute("funcionario") FuncionarioDTO funcionario,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()){
            return "administrator/employee/update";
        }
       boolean saved = service.atualizarFuncionario(funcionario);
        model.addAttribute("sucess", "Funcionário cadastrado com sucesso!");
        return "redirect:/administrator/employees";
    }

    private List<String> tiposFuncionario(){
        List<String> tiposFuncionario = new ArrayList<>();
        for (TipoFuncionario tipo : TipoFuncionario.values()){
            tiposFuncionario.add(tipo.name());
        }
        return tiposFuncionario;
    }
}
