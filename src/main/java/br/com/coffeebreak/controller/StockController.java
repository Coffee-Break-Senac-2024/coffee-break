package br.com.coffeebreak.controller;

import br.com.coffeebreak.dto.EstoqueDTO;
import br.com.coffeebreak.model.estoque.Estoque;
import br.com.coffeebreak.repositories.EstoqueRepository;
import br.com.coffeebreak.service.StockService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/administrator/stock")
public class StockController {

    @Autowired
    private EstoqueRepository estoqueRepository;
    @Autowired
    private StockService service;

    @GetMapping("/create")
    public ModelAndView index(Model model){
        ModelAndView mv = new ModelAndView("administrator/stock/create");
        model.addAttribute("estoque", new EstoqueDTO());
        return mv;
    }

    @PostMapping("/create")
    public ModelAndView create(
            @Valid @ModelAttribute("estoque") EstoqueDTO estoque,
            BindingResult result) {

        ModelAndView mv = new ModelAndView("administrator/stock/create");

        if (result.hasErrors()) {
            return mv;
        }

        try {
            EstoqueDTO saved = service.insert(estoque);
            mv.setViewName("redirect:/administrator/stock/ingredientes");
            mv.addObject("success", "Ingrediente cadastrado com sucesso!");

            return mv;
        } catch (Exception e) {
            mv.addObject("error", "Erro ao cadastrar ingrediente: ");
            mv.setViewName("/administrator/stock/create");

            return mv;
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
    public ModelAndView excluirEstoque(@RequestParam("id") String id) {
        ModelAndView mv = new ModelAndView("administrator/stock/index");
        try {
            this.estoqueRepository.deleteById(id);
            mv.addObject("success", "Excluído com sucesso");

        } catch (Exception e) {
            mv.addObject("error", "Error ao tentar excluír");
        }

        List<Estoque> ingredientes = this.estoqueRepository.findAll();
        mv.addObject("ingredientes", ingredientes);
        return mv;
    }

}
