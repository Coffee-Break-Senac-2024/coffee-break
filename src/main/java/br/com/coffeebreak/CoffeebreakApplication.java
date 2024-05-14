package br.com.coffeebreak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "br.com.coffeebreak")
public class CoffeebreakApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeebreakApplication.class, args);
	}

}
