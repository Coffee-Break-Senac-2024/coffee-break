package br.com.coffeebreak.controller.relatorio;

import br.com.coffeebreak.service.relatorio.RelatorioService;
import br.com.coffeebreak.service.relatorio.strategy.TipoRelatorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/generate")
public class RelatorioController {

    @Autowired
    RelatorioService relatorioService;
    @GetMapping("/create-excel")
    public ModelAndView gerarRelatorio() throws IOException {

        try {
            relatorioService.exportPedidosToExcel(TipoRelatorio.SEMANAL);
            return new ModelAndView("redirect:../");
        } catch (Exception e){
            System.out.println(e);
        }
        return new ModelAndView("redirect:../");
    }



}
