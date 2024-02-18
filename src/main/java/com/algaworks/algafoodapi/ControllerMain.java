package com.algaworks.algafoodapi;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;

@RestController
@RequestMapping("/teste")
public class ControllerMain {

    public CozinhaRepository cozinhaRepository;

    public ControllerMain(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    @GetMapping("/list")
    public List<Cozinha> listar() {
        return cozinhaRepository.listar();
    }

}
