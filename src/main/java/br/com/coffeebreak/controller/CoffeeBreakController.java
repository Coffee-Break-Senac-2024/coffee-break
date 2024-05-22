package br.com.coffeebreak.controller;

import br.com.coffeebreak.dto.UserDTO;
import br.com.coffeebreak.enums.SituacaoPedido;
import br.com.coffeebreak.model.pedido.Pedido;
import br.com.coffeebreak.model.produto.Produto;
import br.com.coffeebreak.service.cliente.ClienteService;
import br.com.coffeebreak.service.produto.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller()
@RequestMapping("/")
public class CoffeeBreakController {

    @Autowired
    private ProdutoService service;

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ModelAndView home(@PageableDefault(size = 8) Pageable pageable){
        ModelAndView mv = new ModelAndView("coffeebreak/home");
        Page<Produto> produtos = service.getAllProducts(pageable);
        mv.addObject("produtos", produtos);
        return mv;
    }

    @GetMapping("/sobre")
    public ModelAndView sobre(){
        ModelAndView mv = new ModelAndView("coffeebreak/sobre");
        return mv;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("coffeebreak/login");
        mv.addObject("user", new UserDTO());
        return mv;
    }

    @GetMapping("/pedidos")
    public ModelAndView pedidos() {
        ModelAndView mv = new ModelAndView("coffeebreak/pedidos");
        List<Pedido> pedidos = clienteService.getPedidosCliente(SituacaoPedido.EM_ANDAMENTO);
        mv.addObject("pedidos", pedidos);
        return mv;
    }

    @GetMapping("/historicoPedidos")
    public ModelAndView historicoPedidos() {
        ModelAndView mv = new ModelAndView("coffeebreak/historicoPedidos");
        List<Pedido> pedidos = clienteService.getPedidosCliente(SituacaoPedido.FINALIZADO);
        mv.addObject("pedidos", pedidos);
        return mv;
    }

    @GetMapping("/descricao")
    public ModelAndView desc (){
        return new ModelAndView("coffeebreak/descricao");
    }

}
