package br.com.coffeebreak.model.cliente.exceptions;

public class ClientAlreadyRegistered extends RuntimeException {
    public ClientAlreadyRegistered(String message) {
        super(message);
    }
}
