package com.danielazevedo.livrariarelacionamentos.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String titulo;

    private int anoPublicacao;

    @ManyToOne
    private Autor autor;

    @ManyToMany(mappedBy = "livros")
    private List<Categoria> categorias = new ArrayList<>();

}
