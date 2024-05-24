package br.com.coffeebreak.controller.administrator;

import br.com.coffeebreak.enums.TipoPedido;
import br.com.coffeebreak.model.pedido.Pedido;
import br.com.coffeebreak.model.produto.Produto;
import br.com.coffeebreak.service.administrator.AdministratorService;
import br.com.coffeebreak.service.pedido.PedidoService;
import br.com.coffeebreak.service.produto.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/administrator")
public class AdministratorController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private AdministratorService administratorService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('GERENTE') || hasAuthority('ATENDENTE')")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("/administrator/index");
        List<Pedido> pedidos = pedidoService.getPedidos();
        mv.addObject("pedidos", pedidos);
        return mv;
    }

    @GetMapping("/novo-pedido")
    public ModelAndView create(){
        ModelAndView mv = new ModelAndView("/administrator/create");
        List<Produto> produtos = produtoService.getAllProducts();
        List<TipoPedido> tipoPedidos = Arrays.asList(TipoPedido.values());
        mv.addObject("produtos", produtos);
        mv.addObject("tipoPedidos", tipoPedidos);
        return mv;
    }

    @PostMapping("/salvar-pedido")
    public ModelAndView salvarPedido(
                                     @RequestParam("nome") String nome,
                                     @RequestParam("tipoPedido") String tipoPedido,
                                     @RequestParam("produtos") List<Produto> produtos
                                     ){
        System.out.println(nome + tipoPedido + produtos);
        administratorService.criarPedido(nome, tipoPedido, produtos);

        return new ModelAndView("redirect:/administrator");
    }

    @PostMapping("/finalizar-pedido/{id}")
    public ModelAndView finalizarPedido(@PathVariable("id") String id){
        pedidoService.FinalizarPedido(id);
        return new ModelAndView("redirect:/administrator");

    }

}
