package com.example.crud.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private long id;
    @Column(name = "nome")
    @Getter
    @Setter
    private String nome;
    @Column(name = "quantidade")
    @Getter
    @Setter
    private int quantidade;
}
