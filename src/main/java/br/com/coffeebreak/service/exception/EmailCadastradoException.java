package br.com.coffeebreak.service.exception;


public class EmailCadastradoException extends RuntimeException {
    public EmailCadastradoException(String message) {
        super(message);
    }
}
