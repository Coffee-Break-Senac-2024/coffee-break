package br.com.coffeebreak.controller;

import br.com.coffeebreak.enums.TipoFuncionario;
import br.com.coffeebreak.model.funcionario.Funcionario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/administrator/employees")
public class EmployeeController {

    @GetMapping
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("adm/employee/index");
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("1",
                "Colaborado 1",
                TipoFuncionario.ATENDENTE,
                "colab1@gmail.com",
                "teste"));
        funcionarios.add(new Funcionario("2",
                "Colaborado 2",
                TipoFuncionario.GERENTE,
                "colab2@gmail.com",
                "teste"));

        mv.addObject("funcionarios", funcionarios);

        return mv;
    }

    @GetMapping("/create")
    public ModelAndView create(){
        ModelAndView mv = new ModelAndView("adm/employee/create");
        List<String> tiposFuncionarios = new ArrayList<>();

        tiposFuncionarios.add(String.valueOf(TipoFuncionario.ATENDENTE));
        tiposFuncionarios.add(String.valueOf(TipoFuncionario.GERENTE));

        mv.addObject("tipoFuncionarios", tiposFuncionarios);
        return mv;
    }

}
