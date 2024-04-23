package br.com.coffeebreak.controller;

import br.com.coffeebreak.model.estoque.Estoque;
import br.com.coffeebreak.model.produto.Produto;
import br.com.coffeebreak.repositories.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/administrator/stock")
public class StockController {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @GetMapping("/create")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("adm/stock/create");
        return mv;
    }

    @PostMapping("/create")
    public ModelAndView cadastrarIngrediente(@ModelAttribute Estoque estoque, RedirectAttributes redirectAttributes) {

        ModelAndView mv = new ModelAndView("adm/stock/create");

        try {
            Estoque saved = this.estoqueRepository.save(estoque);
           mv.addObject("message", "Ingrediente cadastrado com sucesso!");
        } catch (Exception e) {
            mv.addObject("message", "Erro ao cadastrar ingrediente: ");
        }

        return mv;
    }

    @GetMapping("/ingredientes")
    public ModelAndView listarEstoque() {
        ModelAndView mv = new ModelAndView("adm/stock/index");
        List<Estoque> ingredientes = this.estoqueRepository.findAll();
        mv.addObject("ingredientes", ingredientes);
        return mv;

    }

}
