package com.algaworks.algafoodapi.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.algaworks.algafoodapi.domain.model.Restaurante;

@Repository
public interface RestauranteRepository {

    List<Restaurante> listar();

    Restaurante buscar(Long id);

    Restaurante salvar(Restaurante restaurante);

    void remover(Restaurante restaurante);

}
