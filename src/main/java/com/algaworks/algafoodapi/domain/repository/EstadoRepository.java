package com.algaworks.algafoodapi.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.algaworks.algafoodapi.domain.model.Estado;

@Repository
public interface EstadoRepository {

    List<Estado> listar();

    Estado buscar(Long id);

    Estado salvar(Estado estado);

    void remover(Long id);

}