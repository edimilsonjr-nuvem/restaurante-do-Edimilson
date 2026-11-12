package br.com.senai.deliveryavaliacao.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.senai.deliveryavaliacao.service.ProdutoService;
import br.com.senai.deliveryavaliacao.dto.ProdutoRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import br.com.senai.deliveryavaliacao.model.Produto;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.Optional;

@RestController
@RequestMapping ("/produtos")
public class ProdutoController {
    
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Produto> cadastrar(@Valid @RequestBody ProdutoRequest dados) {
        Produto produtoCadastrado = produtoService.cadastrar(dados)
                .orElseThrow(() -> new ResponseStatusException
                (HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao cadastrar o produto"));
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listar() {
        List<Produto> produtos = produtoService.listar();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Integer id) {
        Produto produto = produtoService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException
                (HttpStatus.NOT_FOUND, "Produto não encontrado"));
        return ResponseEntity.ok(produto);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Integer id, 
        @Valid @RequestBody ProdutoRequest novosDados) {

        Optional<Produto> produtoAtualizado = produtoService.atualizar(id, novosDados);

        if (produtoAtualizado.isEmpty()) {
              return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produtoAtualizado.get());
      
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        boolean deletado = produtoService.deletar(id);
        if (!deletado) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
        return ResponseEntity.noContent().build();
    }
}
