package br.com.coffeebreak.controller.stock;

import br.com.coffeebreak.model.estoque.Estoque;
import br.com.coffeebreak.service.stock.EstoqueService;
import br.com.coffeebreak.service.constant.Mensagem;
import br.com.coffeebreak.service.exception.NomeCadastradoException;
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
@RequestMapping("/administrator/stock")
public class EstoqueController {

    @Autowired
    private EstoqueService service;

    @GetMapping("/create")
    public ModelAndView index(Model model){
        ModelAndView mv = new ModelAndView("administrator/stock/create");
        model.addAttribute("estoque", new Estoque());
        return mv;
    }

    @PostMapping("/create")
    public String create(
            @Valid @ModelAttribute("estoque") Estoque estoque,
            BindingResult result,
            RedirectAttributes rd) {

        if (result.hasErrors()) {
            return "administrator/stock/create";
        }

        try {
            service.insert(estoque);
        }catch (NomeCadastradoException e){
            result.rejectValue("nome", e.getMessage(), e.getMessage());
            return "/administrator/stock/create";
        }
        rd.addFlashAttribute("success", Mensagem.ESTOQUE_CRIADO_SUCESSO);
        return "redirect:/administrator/stock/ingredientes";
    }

    @GetMapping("/ingredientes")
    public ModelAndView listarEstoque(
            @RequestParam(required = false) String nome,
            @PageableDefault(size = 5) Pageable pageable) {
        ModelAndView mv = new ModelAndView("administrator/stock/index");

        Page<Estoque> ingredientes;

        if (nome != null && !nome.isEmpty()) {
            ingredientes = service.getIngredientesPage(nome, pageable);
        } else {
            ingredientes = service.getIngredientesPage(pageable);
        }

        mv.addObject("pagina", ingredientes);
        return mv;
    }


    @GetMapping("ingredientes/update")
    public ModelAndView updateEstoque(
            @RequestParam("id") String id,
            Estoque estoque,
            RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView("administrator/stock/create");

        try {
            estoque = service.getIngredientById(id);
        }catch (IllegalArgumentException e){
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return new ModelAndView("redirect:/administrator/stock/ingredientes");
        }
        mv.addObject("estoque", estoque);
        return mv;
    }

    @PostMapping("ingredientes/update")
    public ModelAndView updateEstoque(
            @Valid @ModelAttribute("estoque") Estoque estoque,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        ModelAndView mv = new ModelAndView("administrator/stock/create");
        if (result.hasErrors()) {
            return mv;
        }

        try {
            service.update(estoque);
        }catch (RuntimeException e){
            result.rejectValue("estoque", e.getMessage(), e.getMessage());
            return mv;
        }
        redirectAttributes.addFlashAttribute("success", Mensagem.ESTOQUE_UPDATE_SUCESSO);
        mv.setViewName("redirect:/administrator/stock/ingredientes");
        return mv;
    }

    @PostMapping("/ingredientes/{id}")
    public ModelAndView excluirEstoque(
            @RequestParam("id") String id,
            RedirectAttributes redirectAttributes) {

        try {
            service.delete(id);
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return new ModelAndView("redirect:/administrator/stock/ingredientes");
        }
        redirectAttributes.addFlashAttribute("success", Mensagem.ESTOQUE_DELETADO_SUCESSO);
        return new ModelAndView("redirect:/administrator/stock/ingredientes");
    }
}
