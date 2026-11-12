package br.com.senai.deliveryavaliacao.service;
import org.springframework.stereotype.Service;
import br.com.senai.deliveryavaliacao.repository.RestauranteRepository;
import br.com.senai.deliveryavaliacao.dto.RestauranteRequest;
import br.com.senai.deliveryavaliacao.model.Restaurante;
import java.util.Optional;
import java.util.List;


@Service 
public class RestauranteService {
    
    private final RestauranteRepository restauranteRepository;

    public RestauranteService(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

   public Optional<Restaurante> cadastrar(RestauranteRequest dados) {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(dados.getNome());
        restaurante.setEndereco(dados.getEndereco());
        return Optional.of(restauranteRepository.save(restaurante));
    }   

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Optional<Restaurante> buscarPorId(Integer id) {
        return restauranteRepository.findById(id);
    }

    public Optional<Restaurante> atualizar(Integer id, RestauranteRequest novosDados) {

        Optional<Restaurante> restauranteExistente = restauranteRepository.findById(id.intValue());

        if (restauranteExistente.isEmpty()) {
            return Optional.empty();
        }

        Restaurante restaurante = restauranteExistente.get();
        restaurante.setNome(novosDados.getNome());
        restaurante.setEndereco(novosDados.getEndereco());
        return Optional.of(restauranteRepository.save(restaurante));
    }

    public boolean deletar(Integer id) {
        Optional<Restaurante> restauranteExistente = restauranteRepository.findById(id.intValue());

        if (restauranteExistente.isEmpty()) {
            return false;
        }

        restauranteRepository.deleteById(id);
        return true;
    }
}
