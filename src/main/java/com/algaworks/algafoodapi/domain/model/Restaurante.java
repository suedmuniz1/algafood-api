package com.algaworks.algafoodapi.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    @ManyToOne
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;

    @JsonIgnore
    @Embedded
    private Endereco endereco;

    @JsonIgnore
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime") // datetime = remove os milissegundos ao final do
                                                             // datetime
                                                             // no BD.
    private LocalDateTime dataCadastro;

    @JsonIgnore
    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataAtualizacao;

    @JsonIgnore // Não mostrar a lista de formas de pagamento no json, para que o payload seja
                // mais limpo
    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento", // nome da tabela de associação entre restaurante e forma_pagamento
            joinColumns = @JoinColumn(name = "restaurante_id"), // nome da PK da entidade atual (restaurante)
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id") // nome da PK da entidade associada
                                                                          // (forma_pagamento)
    )
    private List<FormaPagamento> formasPagamento = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "restaurante")
    /*
     * - Consultar a tabela de produtos;
     * - (Para cada restaurante) Achar os produtos que tenham o atributo
     * restaurante, e que o ID do restaurante seja o mesmo;
     * - Atribuir cada objeto encontrado a lista de "produtos" no JSON.
     */
    private List<Produto> produtos = new ArrayList<>();
}
