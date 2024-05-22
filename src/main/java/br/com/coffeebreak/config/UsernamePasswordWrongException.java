package br.com.coffeebreak.config;

public class UsernamePasswordWrongException extends RuntimeException{
    public UsernamePasswordWrongException(String message) {
        super(message);
    }
}
