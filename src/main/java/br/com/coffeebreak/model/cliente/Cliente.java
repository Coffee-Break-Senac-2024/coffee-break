package br.com.coffeebreak.model.cliente;

import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cpf;
    @Column(nullable = false)
    private String endereco;
    @Column(nullable = false)
    private String telefone;


}
