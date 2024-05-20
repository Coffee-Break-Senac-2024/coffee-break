package br.com.coffeebreak.model.funcionario.exceptions;

public class AcessDeniedException extends RuntimeException {

    public AcessDeniedException(String message) {
        super(message);
    }

}
