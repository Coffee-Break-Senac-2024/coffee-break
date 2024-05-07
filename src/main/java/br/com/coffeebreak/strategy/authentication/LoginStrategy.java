package br.com.coffeebreak.strategy.authentication;

import br.com.coffeebreak.dto.TokenDTO;


public interface LoginStrategy {

    TokenDTO login(String username, String password);

}
