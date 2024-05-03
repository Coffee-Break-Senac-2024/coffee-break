package br.com.coffeebreak.service.relatorio.strategy;

import br.com.coffeebreak.model.relatorio.Relatorio;

public enum TipoRelatorio implements RelatorioStrategy {

    DIARIO {
        @Override
        public Relatorio calcular() {
            return null;
        }
    },

    SEMANAL {
        @Override
        public Relatorio calcular() {
            return null;
        }
    },

    MENSAL {
        @Override
        public Relatorio calcular() {
            return null;
        }
    };

}
