package VerdeVida.services;

import VerdeVida.models.Mesa;
import VerdeVida.repositories.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    public Mesa criarMesa(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    public List<Mesa> listarMesas() {
        return mesaRepository.findAll();
    }

    public Optional<Mesa> obterMesaPorId(Long id) {
        return mesaRepository.findById(id);
    }

    public Mesa atualizarMesa(Long id, Mesa mesaAtualizada) {
        return mesaRepository.findById(id)
                .map(mesa -> {
                    mesa.setCapacidade(mesaAtualizada.getCapacidade());
                    mesa.setEstaCheia(mesaAtualizada.isEstaCheia());
                    return mesaRepository.save(mesa);
                })
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));
    }

    public void deletarMesa(Long id) {
        mesaRepository.deleteById(id);
    }
}

