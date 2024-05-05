package br.com.coffeebreak.controller.funcionario;

import br.com.coffeebreak.model.funcionario.Funcionario;
import br.com.coffeebreak.service.funcionario.FuncionarioService;
import br.com.coffeebreak.service.constant.Mensagem;
import br.com.coffeebreak.service.exception.EmailCadastradoException;
import br.com.coffeebreak.service.exception.FuncionarioIdNaoEncontradoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/administrator/employees")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @GetMapping
    public ModelAndView index(
            @RequestParam(required = false) String nome,
            @PageableDefault(size = 3) Pageable pageable) {
        ModelAndView mv = new ModelAndView("administrator/employee/index");

        Page<Funcionario> funcionarios;
        if (nome != null && !nome.isEmpty()) {
           funcionarios = service.getFuncionariosPage(nome, pageable);
        }else{
            funcionarios = service.getFuncionariosPage(pageable);
        }

        mv.addObject("pagina", funcionarios);
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
        redirectAttributes.addFlashAttribute("success", Mensagem.FUNCIOANARIO_CRIADO_SUCESSO);
        return "redirect:/administrator/employees";
    }

    @PostMapping("/delete/{id}")
    public ModelAndView delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
           ModelAndView mv = new ModelAndView("redirect:/administrator/employees");

           try {
                service.deletarPorId(id);
            } catch (FuncionarioIdNaoEncontradoException e) {
                redirectAttributes.addFlashAttribute("error", Mensagem.FUNCIOANARIO_CRIADO_ERROR);
                return mv;
            }
        redirectAttributes.addFlashAttribute("success", Mensagem.FUNCIOANARIO_DELETADO_SUCESSO);
        return mv;
    }


    @GetMapping("/update/{id}")
    public ModelAndView atualizarFuncionario(@PathVariable String id) {
        ModelAndView mv = new ModelAndView("administrator/employee/create");
        Funcionario funcionario = service.getFuncionarioPorID(id);
        mv.addObject("funcionario", funcionario);
        mv.addObject("tiposFuncionario", service.tiposFuncionario());
        return mv;
    }

    @PostMapping("/update")
    public String atualizarFuncionario(
            @Valid @ModelAttribute("funcionario") Funcionario funcionario,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {

        model.addAttribute("tiposFuncionario", service.tiposFuncionario());

        if (bindingResult.hasErrors()) {
            return "administrator/employee/create";
        }

        try {
            service.atualizarFuncionario(funcionario);
        } catch (FuncionarioIdNaoEncontradoException e) {
            bindingResult.rejectValue("id", e.getMessage(), e.getMessage());
        }
        redirectAttributes.addFlashAttribute("success", Mensagem.FUNCIOANARIO_UPDATE_SUCESSO);
        return "redirect:/administrator/employees";
    }
}
