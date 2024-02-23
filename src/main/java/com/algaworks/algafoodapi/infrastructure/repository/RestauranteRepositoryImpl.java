package com.algaworks.algafoodapi.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.algaworks.algafoodapi.domain.model.Restaurante;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class RestauranteRepositoryImpl {

    @PersistenceContext
    private EntityManager manager;

    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

        var jpql = "from Restaurante where nome like :nome and taxaFrete between :taxaFreteInicial and :taxaFreteFinal";

        return manager.createQuery(jpql, Restaurante.class)
                .setParameter("nome", "%" + nome + "%")
                .setParameter("taxaFreteInicial", taxaFreteInicial)
                .setParameter("taxaFreteFinal", taxaFreteFinal)
                .getResultList();
    }

}
