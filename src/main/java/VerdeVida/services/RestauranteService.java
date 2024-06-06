package VerdeVida.services;

import VerdeVida.models.Restaurante;
import VerdeVida.repositories.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    public Restaurante criarRestaurante(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

    public List<Restaurante> listarRestaurantes() {
        return restauranteRepository.findAll();
    }

    public Optional<Restaurante> obterRestaurantePorId(Long id) {
        return restauranteRepository.findById(id);
    }

    public Restaurante atualizarRestaurante(Long id, Restaurante restauranteAtualizado) {
        return restauranteRepository.findById(id)
                .map(restaurante -> {
                    restaurante.setNome(restauranteAtualizado.getNome());
                    restaurante.setCardapio(restauranteAtualizado.getCardapio());
                    restaurante.setMesas(restauranteAtualizado.getMesas());
                    restaurante.setLista_requisicoes(restauranteAtualizado.getLista_requisicoes());
                    restaurante.setFila_espera(restauranteAtualizado.getFila_espera());
                    restaurante.setPedidos(restauranteAtualizado.getPedidos());
                    return restauranteRepository.save(restaurante);
                })
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));
    }

    public void deletarRestaurante(Long id) {
        restauranteRepository.deleteById(id);
    }
}

