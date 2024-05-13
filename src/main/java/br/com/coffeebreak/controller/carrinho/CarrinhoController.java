package br.com.coffeebreak.controller.carrinho;

import br.com.coffeebreak.model.ItemProduto.ItemProduto;
import br.com.coffeebreak.service.carrinho.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CarrinhoController {

    @Autowired
    CarrinhoService service;

    @PostMapping("/meu-carrinho")
    public String index(@RequestParam("produtoId") String id, @RequestParam("quantidade") int quantidade,
                        @RequestParam("preco") double preco){
        service.adicionarItem(id, quantidade, preco);
        return "redirect:/";
    }

    @GetMapping("/meu-carrinho")
    public ModelAndView carrinho(){
        ModelAndView mv = new ModelAndView("coffeebreak/carrinho");
        List<ItemProduto> pedidos =  service.getItensCarrinho();
        Double precoTotal = service.calcularTotalCarrinho();
        int items = service.getQuantidadeCarrinho();
        mv.addObject("pedidos", pedidos);
        mv.addObject("precoTotal", precoTotal);
        mv.addObject("items", items);
        return mv;
    }

    @PostMapping("/meu-carrinho/remover/{id}")
    public String removerItem(@RequestParam("id") int id){
        System.out.println("id " + id);
        service.removerItem(id);
        return "redirect:/meu-carrinho";
    }

}
