package br.com.coffeebreak.security;

public class UsernamePasswordWrongException extends RuntimeException{
    public UsernamePasswordWrongException(String message) {
        super(message);
    }
}
