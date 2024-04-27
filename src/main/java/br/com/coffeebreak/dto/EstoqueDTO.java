package br.com.coffeebreak.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class EstoqueDTO {
    @NotEmpty(message = "O nome é obrigatório.")
    @Size(max = 20, message = "O limite de caracteres é 20.")
    private String name;
    @NotNull(message = "A quantidade é obrigatória.")
    @Min(value = 1, message = "A quantidade deve ser maior que zero.")
    @Max(value = 100, message = "A quantidade deve ser menor que 100.")
    private int quantity;
}
