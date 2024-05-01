package br.com.coffeebreak.service.exception;

public class NomeCadastradoException extends IllegalArgumentException {
    public NomeCadastradoException(String message) {
        super(message);
    }
}
