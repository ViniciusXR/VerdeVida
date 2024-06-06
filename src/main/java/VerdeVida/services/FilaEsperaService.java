package VerdeVida.services;

import VerdeVida.models.FilaEspera;
import VerdeVida.models.Requisicao;
import VerdeVida.repositories.FilaEsperaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilaEsperaService {

    @Autowired
    private FilaEsperaRepository filaEsperaRepository;

    public FilaEspera criarFilaEspera(FilaEspera filaEspera) {
        return filaEsperaRepository.save(filaEspera);
    }

    public List<FilaEspera> listarFilasEspera() {
        return filaEsperaRepository.findAll();
    }

    public Optional<FilaEspera> obterFilaEsperaPorId(Long id) {
        return filaEsperaRepository.findById(id);
    }

    public FilaEspera atualizarFilaEspera(Long id, FilaEspera filaEsperaAtualizada) {
        return filaEsperaRepository.findById(id)
                .map(filaEspera -> {
                    filaEspera.setRequisicoes(filaEsperaAtualizada.getRequisicoes());
                    filaEspera.setTamanho(filaEsperaAtualizada.getTamanho());
                    return filaEsperaRepository.save(filaEspera);
                })
                .orElseThrow(() -> new RuntimeException("Fila de espera não encontrada"));
    }

    public void deletarFilaEspera(Long id) {
        filaEsperaRepository.deleteById(id);
    }

    public FilaEspera adicionarRequisicao(Long filaEsperaId, Requisicao requisicao) {
        return filaEsperaRepository.findById(filaEsperaId)
                .map(filaEspera -> {
                    filaEspera.adicionar(requisicao);
                    return filaEsperaRepository.save(filaEspera);
                })
                .orElseThrow(() -> new RuntimeException("Fila de espera não encontrada"));
    }

    public FilaEspera removerRequisicao(Long filaEsperaId) {
        return filaEsperaRepository.findById(filaEsperaId)
                .map(filaEspera -> {
                    filaEspera.remover();
                    return filaEsperaRepository.save(filaEspera);
                })
                .orElseThrow(() -> new RuntimeException("Fila de espera não encontrada"));
    }

    public Requisicao obterProximaRequisicao(Long filaEsperaId) {
        return filaEsperaRepository.findById(filaEsperaId)
                .map(FilaEspera::getProxima)
                .orElseThrow(() -> new RuntimeException("Fila de espera não encontrada"));
    }
}
