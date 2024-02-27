package com.algaworks.algafoodapi.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafoodapi.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {
    /*
     * - Em JpaRepository<Cozinha, Long> => Cozinha é a entidade Entidade a ser
     * mapeada, Long: Chave primária da Entidade
     * 
     * - Keywords que podem ser usadas no nome do método para fazer consultas no BD:
     * -> https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
     */

    List<Cozinha> findByNome(String nome);

    List<Cozinha> findTodasAsCozinhasByNomeContaining(String nome);

    Optional<Cozinha> findUnicaCozinhaByNome(String nome);
}
