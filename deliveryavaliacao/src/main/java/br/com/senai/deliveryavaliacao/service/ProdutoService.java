package br.com.senai.deliveryavaliacao.service;

import org.springframework.stereotype.Service;
import br.com.senai.deliveryavaliacao.repository.ProdutoRepository;
import br.com.senai.deliveryavaliacao.model.Produto;
import java.util.Optional;
import java.util.List;
import br.com.senai.deliveryavaliacao.dto.ProdutoRequest;
import br.com.senai.deliveryavaliacao.repository.RestauranteRepository;
import br.com.senai.deliveryavaliacao.model.Restaurante;

@Service 
public class ProdutoService {
    
    private final ProdutoRepository produtoRepository;
    private final RestauranteRepository restauranteRepository;

    public ProdutoService(ProdutoRepository produtoRepository, RestauranteRepository restauranteRepository) {
        this.produtoRepository = produtoRepository;
        this.restauranteRepository = restauranteRepository;
    }

    public Optional<Produto> cadastrar(ProdutoRequest dados) {

        Optional<Restaurante> restauranteExistente = restauranteRepository.findById(dados.getRestauranteId());
        if (restauranteExistente.isEmpty()) {
            return Optional.empty();
        }

        Produto produto = new Produto();
       produto.setNome(dados.getNome());
       produto.setPreco(dados.getPreco());
       produto.setRestaurante(restauranteExistente.get());

        return Optional.of(produtoRepository.save(produto));
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Integer id) {
        return produtoRepository.findById(id);
    }

    public Optional<Produto> atualizar(Integer id, ProdutoRequest novosDados) {

        Optional<Produto> produtoExistente = produtoRepository.findById(id);


        if (produtoExistente.isEmpty()) {
            return Optional.empty();
        }

        Produto produto = produtoExistente.get();
        produto.setNome(novosDados.getNome());
        produto.setPreco(novosDados.getPreco());
        return Optional.of(produtoRepository.save(produto));
    }

    public boolean deletar(Integer id) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id.intValue());

        if (produtoExistente.isEmpty()) {
            return false;
        }

        produtoRepository.deleteById(id);
        return true;
    }
}
