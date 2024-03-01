package com.algaworks.algafoodapi.domain.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Grupo {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToMany
    /*
     * - (Para cada grupo) Pegar os permissao_id associados ao ID do grupo atual na
     * tabela grupo_permissao;
     * - Recuperar os objetos encontrados na tabela permissao no BD, e colocar na
     * lista de "permissoes".
     */
    @JoinTable(name = "grupo_permissao", // nome da tabela de associação entre restaurante e forma_pagamento
            joinColumns = @JoinColumn(name = "grupo_id"), // nome da PK da entidade atual (restaurante)
            inverseJoinColumns = @JoinColumn(name = "permissao_id") // nome da PK da entidade associada
                                                                    // (forma_pagamento)
    )
    private List<Permissao> permissoes = new ArrayList<>();

}