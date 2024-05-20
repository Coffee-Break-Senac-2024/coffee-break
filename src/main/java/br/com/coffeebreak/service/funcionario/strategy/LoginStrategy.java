package br.com.coffeebreak.service.funcionario.strategy;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@Component
public interface LoginStrategy {

    void login(String username, String password);

}
