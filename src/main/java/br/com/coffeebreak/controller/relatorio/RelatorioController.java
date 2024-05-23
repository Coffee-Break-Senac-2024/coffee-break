package br.com.coffeebreak.controller.relatorio;

import br.com.coffeebreak.service.relatorio.RelatorioService;
import br.com.coffeebreak.service.relatorio.strategy.TipoRelatorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/administrator/relatorio")
public class RelatorioController {


    @Autowired
    RelatorioService relatorioService;



    @GetMapping
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("administrator/relatorio/generate");
        mv.addObject("tipoRelatorio",relatorioService.getTiposRelatorio());
        return mv;
    }
    @GetMapping("/create-excel")
    public ModelAndView gerarRelatorio(@RequestParam("tipoRelatorio") String tipoRelatorioString) throws IOException {
        ModelAndView mv = new ModelAndView("administrator/relatorio/generate");

        try {
            TipoRelatorio tipoRelatorio = TipoRelatorio.valueOf(tipoRelatorioString);
            mv.addObject("tipoRelatorio", tipoRelatorio);
            relatorioService.exportPedidosToExcel(tipoRelatorio);
            mv.addObject("successMessage", "Planilha criada na pasta C:Relatorios");

        } catch (IllegalArgumentException e) {
            mv.addObject("errorMessage", "Valor inválido para TipoRelatorio " + tipoRelatorioString);

        } catch (Exception e) {
            mv.addObject("errorMessage", "Erro ao gerar planilha");

        }
        return mv;

    }
}
