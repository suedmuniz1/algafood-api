package com.algaworks.algafoodapi.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cozinha> listar() {
        TypedQuery<Cozinha> query = manager.createQuery("from Cozinha", Cozinha.class);
        return query.getResultList();
    }

    @Override
    public Cozinha buscar(Long id) {
        return manager.find(Cozinha.class, id);
    }

    // serve tanto pra adicionar como para alterar
    // pra alterar, é só passar uma instância do objeto Cozinha, em que a instância
    // ja tem um id existente no banco
    @Transactional
    @Override
    public Cozinha salvar(Cozinha cozinha) {
        return manager.merge(cozinha);
    }

    @Transactional
    @Override
    public void remover(Cozinha cozinha) {
        // precisa fazer essa busca por ID e associar a variável cozinha
        // a cozinha que veio por parâmetro não é gerenciada pelo JPA (ela é apenas uma
        // instância), e por esse motivo
        // o método remove não consegue excluir a cozinha que veio por parâmetro (dá
        // erro)
        // Já atribuindo o resultado de buscar a cozinha, como buscar é do JPA (find), a
        // instancia retornada é uma instância gerenciada pelo JPA

        cozinha = buscar(cozinha.getId());
        manager.remove(cozinha);
    }

}
