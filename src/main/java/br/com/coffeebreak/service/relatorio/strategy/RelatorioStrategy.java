package br.com.coffeebreak.service.relatorio.strategy;

import br.com.coffeebreak.model.relatorio.Relatorio;

import java.time.LocalDateTime;

public interface RelatorioStrategy {

    LocalDateTime calcular();

}
