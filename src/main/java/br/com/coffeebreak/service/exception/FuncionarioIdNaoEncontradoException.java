package br.com.coffeebreak.service.exception;

public class FuncionarioIdNaoEncontradoException extends RuntimeException {
    public FuncionarioIdNaoEncontradoException(String message) {
        super(message);
    }
}
