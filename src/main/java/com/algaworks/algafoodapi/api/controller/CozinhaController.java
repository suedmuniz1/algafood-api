package com.algaworks.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import com.algaworks.algafoodapi.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping(value = "cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @GetMapping // (produces = MediaType.APPLICATION_JSON_VALUE) -> setar o mediaType retornado
    public ResponseEntity<List<Cozinha>> listar() {
        return ResponseEntity.ok(cozinhaRepository.listar());
    }

    // @ResponseStatus(HttpStatus.CREATED) - Exemplo de como usar o @ResponseStatus
    // para mudar o código de status da request
    @GetMapping("{cozinhaId}")
    // o @PathVariable também aceita o bind como parâmetro. Ex.:
    // @PathVariable("cozinhaId") Long id
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
        /*
         * - EXEMPLO DE COMO RETORNAR STATUS HTTP COM BODY
         * return ResponseEntity.status(HttpStatus.OK).body(cozinha);
         * 
         * - ATALHO PARA O EXEMPLO ACIMA
         * return ResponseEntity.ok(cozinha); // Atalho para o exemplo acima
         * 
         * - EXEMPLO DE RETORNO COM HEADERS (NO POSTMAN, LEMBRAR DE DESATIVAR A OPÇÃO
         * "automatically follow redirect")
         * 
         * HttpHeaders headers = new HttpHeaders();
         * headers.add(HttpHeaders.LOCATION, "http://localhost:8080/cozinhas");
         * 
         * return ResponseEntity
         * .status(HttpStatus.FOUND)
         * .headers(headers)
         * .build();
         * 
         */

        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);

        if (cozinha != null) {
            return ResponseEntity.ok(cozinha);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
        Cozinha cozinhaSalva = cadastroCozinha.salvar(cozinha);
        return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaSalva);
    }

    @PutMapping("{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
        Cozinha cozinhaAtual = cozinhaRepository.buscar(cozinhaId);

        if (cozinhaAtual == null || cozinha == null) {
            return ResponseEntity.notFound().build();
        } else {
            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

            cozinhaAtual = cadastroCozinha.salvar(cozinhaAtual);
            return ResponseEntity.ok(cozinhaAtual);
        }
    }

    @DeleteMapping("{cozinhaId}")
    public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId) {
        try {
            cadastroCozinha.excluir(cozinhaId);

            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
