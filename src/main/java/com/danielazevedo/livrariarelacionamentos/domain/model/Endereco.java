package com.danielazevedo.livrariarelacionamentos.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String rua;

    private String cidade;

    private String estado;

    private String pais;

    @OneToOne(mappedBy = "endereco")
    private Editora editora;
}
