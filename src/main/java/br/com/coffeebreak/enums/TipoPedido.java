package br.com.coffeebreak.enums;

public enum TipoPedido {

    BALCAO, ENTREGA;

    public static TipoPedido getTipoPedido(String nome) {
        for (TipoPedido tipo : TipoPedido.values()) {
            if (tipo.name().equalsIgnoreCase(nome)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de pedido não encontrado: " + nome);
    }
}
