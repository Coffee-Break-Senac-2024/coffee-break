package br.com.coffeebreak.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EstoqueDTO {
    @NotEmpty(message = "O nome é obrigatório.")
    private String name;
    @NotNull(message = "A quantidade é obrigatória.")
    @Min(value = 1, message = "A quantidade deve ser maior que zero.")
    private int quantity;
}
