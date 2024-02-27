package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import com.algaworks.algafoodapi.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.algaworks.algafoodapi.infrastructure.repository.spec.RestauranteSpecs.comFreteGratis;
import static com.algaworks.algafoodapi.infrastructure.repository.spec.RestauranteSpecs.comNomeSemelhante;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @GetMapping
    public ResponseEntity<List<Restaurante>> listar() {
        return ResponseEntity.ok(restauranteRepository.findAll());
    }

    @GetMapping("/first-by-nome/{nome}")
    public Object recuperarPrimeiroResultadoPorNome(@PathVariable String nome) {
        return restauranteRepository.findFirstRestauranteByNomeContaining(nome);
    }

    @GetMapping("/exists")
    public boolean checarSeExistePorNome(@RequestParam String nome) {
        return restauranteRepository.existsByNome(nome);
    }

    @GetMapping("/count-by-cozinha/{cozinhaId}")
    public int contarPorCozinhaId(@PathVariable Long cozinhaId) {
        return restauranteRepository.countByCozinhaId(cozinhaId);
    }

    @GetMapping("/get-by-nome-and-cozinha-id")
    public List<Restaurante> consultarPorNomeECozinhaId(String nome, Long cozinhaId) {
        return restauranteRepository.consultarPorNomeECozinhaId(nome, cozinhaId);
    }

    @GetMapping("/por-nome-e-frete")
    public List<Restaurante> getMethodName(@RequestParam(required = false) String nome, @RequestParam(required = false) BigDecimal taxaFreteInicial,
            @RequestParam(required = false) BigDecimal taxaFreteFinal) {
        return restauranteRepository.consultar(nome, taxaFreteInicial, taxaFreteFinal);
    }

    @GetMapping("/com-frete-gratis")
    public List<Restaurante> restaurantesComFreteGratis(@RequestParam("nome") String nome) {
        return restauranteRepository.findComFreteGratis(nome);
    }

    @GetMapping("/primeiro")
    public Optional<Restaurante> primeiroRestaurante() {
        return restauranteRepository.buscarPrimeiro();
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {
        Optional<Restaurante> restaurante = restauranteRepository.findById(restauranteId);

        return restaurante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
        try {
            restaurante = cadastroRestaurante.salvar(restaurante);

            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<?> atualizar(@PathVariable Long restauranteId,
            @RequestBody(required = true) Restaurante restaurante) {
        Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);

        if (restauranteAtual.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            try {
                BeanUtils.copyProperties(restaurante, restauranteAtual.get(), "id");

                return ResponseEntity.ok(cadastroRestaurante.salvar(restauranteAtual.get()));
            } catch (EntidadeNaoEncontradaException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
    }

    @PatchMapping("/{restauranteId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
            @RequestBody Map<String, Object> restaurante) {
        Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);

        if (restauranteAtual.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Restaurante restauranteEncontrado = restauranteAtual.get();
        merge(restaurante, restauranteEncontrado);

        return ResponseEntity.ok(restauranteRepository.save(restauranteEncontrado));
    }

    private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restaurante = objectMapper.convertValue(camposOrigem, Restaurante.class);

        camposOrigem.forEach((chave, valor) -> {

            Field field = ReflectionUtils.findField(Restaurante.class, chave);

            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restaurante);
            ReflectionUtils.setField(field, restauranteDestino, novoValor);

        });
    }
}
