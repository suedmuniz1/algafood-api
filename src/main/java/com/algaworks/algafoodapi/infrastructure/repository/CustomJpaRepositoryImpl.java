package com.algaworks.algafoodapi.infrastructure.repository;

import com.algaworks.algafoodapi.domain.repository.CustomJpaRepository;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.Optional;

public class CustomJpaRepositoryImpl<T, ID> // precisa do <T, ID> pois o T vai ser genérico e a classe super precisa
        extends SimpleJpaRepository<T, ID>  // Repo padrão do JPA
        implements CustomJpaRepository<T, ID> // interface com os custom methods
{

    private final EntityManager manager;

    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.manager = entityManager;
    }

    @Override
    public Optional<T> buscarPrimeiro() {
        // getDomainClass() => Gets the class of the entity where the method is being used.
        String jpql = "from " + getDomainClass().getName();

        T entity = manager.createQuery(jpql, getDomainClass())
                .setMaxResults(1) // LIMIT 1
                .getSingleResult();

        return Optional.ofNullable(entity);
    }

}
