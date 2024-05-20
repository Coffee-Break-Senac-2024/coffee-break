package br.com.coffeebreak.enums;

public enum TipoFuncionario {
    ADMIN("ADMIN"),
    ATENDENTE("ATENDENTE"),
    GERENTE("GERENTE");

    private final String valor;

    TipoFuncionario(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
