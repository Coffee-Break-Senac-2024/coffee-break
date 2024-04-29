package br.com.coffeebreak.dto;

import br.com.coffeebreak.enums.TipoFuncionario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Data
public class FuncionarioDTO {

    private String id;

    @NotEmpty(message = "O campo nome é obrigatótio.")
    private String nome;

    @NotNull(message = "O campo tipo de funcinário é obrigatótio.")
    private TipoFuncionario tipoFuncionario;

    @Email(message = "O campo email é obrigatótio.")
    private String email;

    @NotEmpty(message = "O campo nome é obrigatótio")
    @Size(max = 14, min = 8, message = "O campo senha dever ter no minímo 8 e maxímo 14 caracteres.")
    private String senha;

    @NotNull(message = "A data não pode ser nula.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime entrada;

    private LocalDateTime saida;

}
