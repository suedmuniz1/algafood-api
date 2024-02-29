package com.algaworks.algafoodapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cozinha {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    // @JsonProperty("nomeDesejado") -> Muda o nome
    // @JsonIgnore -> Ignora o campo no Json retornado na request
    @Column(nullable = false)
    private String nome;

    @JsonIgnore //Para evitar a serialização infinita no json da request (Ex:cozinha e restaurante travando o server)
    @OneToMany(mappedBy = "cozinha") /* mappedBy = nome do atributo no objeto do tipo Restaurante
     * O inverso do que é definido para cozinha em Restaurante - 1 cozinha = N restaurantes
     * O valor inInicializar vazio é importante para não dar NullPointer em algum caso */
    private List<Restaurante> restaurantes = new ArrayList<>();
}
