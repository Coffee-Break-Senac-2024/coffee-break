package br.com.coffeebreak.service.relatorio.strategy;


import java.time.LocalDateTime;

public enum TipoRelatorio implements RelatorioStrategy {

    DIARIO {

        @Override
        public LocalDateTime calcular() {
            LocalDateTime inicio = LocalDateTime.now();
            return inicio.minusDays(1);
        }


    },

    SEMANAL {
        @Override
        public LocalDateTime calcular() {
            LocalDateTime inicio = LocalDateTime.now();
            return inicio.minusDays(7);
        }
    },

    MENSAL {
        @Override
        public LocalDateTime calcular() {
            LocalDateTime inicio = LocalDateTime.now();
            return inicio.minusMonths(1);
        }
    }


}
