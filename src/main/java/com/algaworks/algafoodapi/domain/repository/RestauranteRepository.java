package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, RestauranteRepositoryQueries {

    /* ------ CONSULTAS POR KEYWORDS NA DEFINIÇÃO DO MÉTODO ---------- */
    /* Consultas simples que dá pra usar as keywords */

    Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);
    // pra recuperar o top 2 restaurantes no exemplo acima, poderia ser usado o
    // findTop2RestaurantesBy...()

    boolean existsByNome(String nome);

    int countByCozinhaId(Long cozinhaId); // O SDJ consegue identificar que o Id está dentro de Cozinha

    /*------ CONSULTAS VIA @Query OU DEFINIDAS NO orm.xml -------- */
    /* Consultas que se feitas com keywords ficariam bagunçadas (jpql extensas) */

    /*
     * List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long
     * cozinhaId);
     * O método acima funciona, porém a forma abaixo é mais semântica para outros
     * devs entenderem
     */

    // @Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
    List<Restaurante> consultarPorNomeECozinhaId(String nome, @Param("id") Long cozinhaId);
    /*
     * Para melhorar a legibilidade para queries muito extensas, criar o arquivo
     * orm.xml e colocar as queries lá usando "names-query"
     * 
     */

}
