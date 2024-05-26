package br.com.coffeebreak.service;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public abstract class Helpers {

    public static double formatarValor(Double valor) {
        NumberFormat formatter = new DecimalFormat("#,###.00");
        String valorFormatado = formatter.format(valor);
        valorFormatado = valorFormatado.replace(",", ".");
        return Double.parseDouble(valorFormatado);
    }
}
