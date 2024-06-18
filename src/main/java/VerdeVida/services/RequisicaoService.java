package VerdeVida.services;

import java.lang.System.Logger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import VerdeVida.models.FilaEspera;
import VerdeVida.models.Mesa;
import VerdeVida.models.Requisicao;
import VerdeVida.repositories.MesaRepository;
import VerdeVida.repositories.RequisicaoRepository;

@Service
public class RequisicaoService {

    @Autowired
    private RequisicaoRepository requisicaoRepository;

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private FilaEsperaService filaEsperaService;

    public Requisicao criarRequisicao(Requisicao requisicao) {
        return requisicaoRepository.save(requisicao);
    }

    public void alocarRequisicao(Requisicao requisicao) {
        List<Mesa> mesasDisponiveis = mesaRepository.findAll().stream()
                .filter(mesa -> !mesa.isEstaCheia() && mesa.getCapacidade() >= requisicao.getQuantidade_pessoas())
                .collect(Collectors.toList());

        if (!mesasDisponiveis.isEmpty()) {
            Mesa mesaAlocada = mesasDisponiveis.get(0);
            mesaAlocada.setEstaCheia(true);
            mesaAlocada.setRequisicao(requisicao);
            mesaRepository.save(mesaAlocada);

            requisicao.setStatus("Atendida");
            requisicao.setMesa(mesaAlocada);
            requisicaoRepository.save(requisicao);
        } else {
            // Adicionar na fila de espera se não houver mesas disponíveis
            FilaEspera filaEspera = filaEsperaService.listarFilasEspera().stream()
                    .findFirst()
                    .orElse(new FilaEspera());
            filaEspera.adicionar(requisicao);
            filaEsperaService.criarFilaEspera(filaEspera);
        }
    }

    public List<Requisicao> listarRequisicoes() {
        return requisicaoRepository.findAll();
    }

    public Optional<Requisicao> obterRequisicaoPorId(Long id) {
        return requisicaoRepository.findById(id);
    }

    public Requisicao atualizarRequisicao(Long id, Requisicao requisicaoAtualizada) {
        return requisicaoRepository.findById(id)
                .map(requisicao -> {
                    requisicao.setStatus(requisicaoAtualizada.getStatus());
                    requisicao.setQuantidade_pessoas(requisicaoAtualizada.getQuantidade_pessoas());
                    requisicao.setHora_entrada(requisicaoAtualizada.getHora_entrada());
                    requisicao.setHora_saida(requisicaoAtualizada.getHora_saida());
                    requisicao.setCliente(requisicaoAtualizada.getCliente());
                    return requisicaoRepository.save(requisicao);
                })
                .orElseThrow(() -> new RuntimeException("Requisicao não encontrada"));
    }

    public void deletarRequisicao(Long id) {
        requisicaoRepository.deleteById(id);
    }
}
