package com.algaworks.algafoodapi.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.algaworks.algafoodapi.domain.model.Permissao;

@Repository
public interface PermissaoRepository {

    List<Permissao> listar();

    Permissao buscar(Long id);

    Permissao salvar(Permissao permissao);

    void remover(Permissao permissao);

}
