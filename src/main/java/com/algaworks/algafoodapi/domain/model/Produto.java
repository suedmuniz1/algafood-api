package com.algaworks.algafoodapi.domain.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private boolean ativo;

    @JsonIgnore // Para evitar loop infinito de serialização ao gerar o JSON
    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    /*
     * - Consultar a tabela de restaurante;
     * - (Para cada Produto) Achar o restaurante que tenha o ID igual ao
     * restaurante_id registrado no produto;
     * - Atribuir o objeto encontrado ao produto como "restaurante" no JSON.
     */
    private Restaurante restaurante;

}
