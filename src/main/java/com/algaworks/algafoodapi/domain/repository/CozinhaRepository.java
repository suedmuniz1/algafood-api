package com.algaworks.algafoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafoodapi.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
    // Em JpaRepository<Cozinha, Long> =>
    // Cozinha é a entidade Entidade a ser mapeada
    // Long: Chave primária da Entidade
}
