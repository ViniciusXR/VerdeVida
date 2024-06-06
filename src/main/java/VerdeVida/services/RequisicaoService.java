package VerdeVida.services;

import VerdeVida.models.Requisicao;
import VerdeVida.repositories.RequisicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequisicaoService {

    @Autowired
    private RequisicaoRepository requisicaoRepository;

    public Requisicao criarRequisicao(Requisicao requisicao) {
        return requisicaoRepository.save(requisicao);
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
                    requisicao.setMesa_associada(requisicaoAtualizada.getMesa_associada());
                    requisicao.setComanda(requisicaoAtualizada.getComanda());
                    return requisicaoRepository.save(requisicao);
                })
                .orElseThrow(() -> new RuntimeException("Requisicao não encontrada"));
    }

    public void deletarRequisicao(Long id) {
        requisicaoRepository.deleteById(id);
    }
}
