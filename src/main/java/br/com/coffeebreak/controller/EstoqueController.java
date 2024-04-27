package br.com.coffeebreak.controller;

import br.com.coffeebreak.dto.EstoqueDTO;
import br.com.coffeebreak.model.estoque.Estoque;
import br.com.coffeebreak.repositories.EstoqueRepository;
import br.com.coffeebreak.service.EstoqueService;
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
@RequestMapping("/administrator/stock")
public class EstoqueController {

    @Autowired
    private EstoqueRepository estoqueRepository;
    @Autowired
    private EstoqueService service;

    @GetMapping("/create")
    public ModelAndView index(Model model){
        ModelAndView mv = new ModelAndView("administrator/stock/create");
        model.addAttribute("estoque", new EstoqueDTO());
        return mv;
    }

    @PostMapping("/create")
    public String create(
            @Valid @ModelAttribute("estoque")
            EstoqueDTO estoque,
            BindingResult result,
            RedirectAttributes rd)
    {
        if (result.hasErrors()) {
            return "administrator/stock/create";
        }

        boolean saved = service.insert(estoque);

        if(saved) {
            rd.addFlashAttribute("success", "Ingrediente cadastrado com sucesso!");
            return "redirect:/administrator/stock/ingredientes";
        } else {
            rd.addFlashAttribute("error", "Erro ao cadastrar ingrediente: ");
            return "/administrator/stock/create";
        }
    }

    @GetMapping("/ingredientes")
    public ModelAndView listarEstoque() {
        ModelAndView mv = new ModelAndView("administrator/stock/index");
        List<Estoque> ingredientes = this.estoqueRepository.findAll();
        mv.addObject("ingredientes", ingredientes);
        return mv;
    }

    @PostMapping("/ingredientes/{id}")
    public String excluirEstoque(
            @RequestParam("id") String id,
            RedirectAttributes redirectAttributes) {

        try {
            this.estoqueRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Excluído com sucesso");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error ao tentar excluír");
        }

        return "redirect:/administrator/stock/ingredientes";
    }

}
