package br.com.senai.deliveryavaliacao.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.senai.deliveryavaliacao.service.RestauranteService;
import br.com.senai.deliveryavaliacao.dto.RestauranteRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import br.com.senai.deliveryavaliacao.model.Restaurante;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


@RestController 
@RequestMapping ("/restaurantes")
public class RestauranteController {
    
    private final RestauranteService restauranteService;

    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @PostMapping
    public ResponseEntity<Restaurante> cadastrar(@Valid @RequestBody RestauranteRequest dados) {
        Restaurante restauranteCadastrado = restauranteService.cadastrar(dados)
                .orElseThrow(() -> new ResponseStatusException
                (HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao cadastrar o restaurante"));
        return ResponseEntity.status(HttpStatus.CREATED).body(restauranteCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<Restaurante>> listar() {
        List<Restaurante> restaurantes = restauranteService.listar();
        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscarPorId(@PathVariable Integer id) {
        Optional<Restaurante> restaurante = restauranteService.buscarPorId(id);

       if (restaurante.isPresent()) {
            return ResponseEntity.ok(restaurante.get());
        } 
            return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> atualizar(@PathVariable Integer id, 
        @Valid @RequestBody RestauranteRequest novosDados) {

        Optional<Restaurante> restauranteAtualizado = restauranteService.atualizar(id, novosDados);

        if (restauranteAtualizado.isEmpty()) {
            return ResponseEntity.notFound().build();
             
        } 
        return ResponseEntity.ok(restauranteAtualizado.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        boolean deletado = restauranteService.deletar(id);

        if (deletado) {
            return ResponseEntity.noContent().build();
        } 
            return ResponseEntity.notFound().build();
    }
}
