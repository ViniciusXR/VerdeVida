package VerdeVida.services;

import VerdeVida.models.Comanda;
import VerdeVida.models.Pedido;
import VerdeVida.repositories.ComandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComandaService {

    @Autowired
    private ComandaRepository comandaRepository;

    public Comanda criarComanda(Comanda comanda) {
        return comandaRepository.save(comanda);
    }

    public List<Comanda> listarComandas() {
        return comandaRepository.findAll();
    }

    public Optional<Comanda> obterComandaPorId(Long id) {
        return comandaRepository.findById(id);
    }

    public Comanda atualizarComanda(Long id, Comanda comandaAtualizada) {
        return comandaRepository.findById(id)
                .map(comanda -> {
                    comanda.setLista_pedido(comandaAtualizada.getLista_pedido());
                    comanda.setValor_total(comandaAtualizada.getValor_total());
                    comanda.setEstaFechada(comandaAtualizada.isEstaFechada());
                    return comandaRepository.save(comanda);
                })
                .orElseThrow(() -> new RuntimeException("Comanda não encontrada"));
    }

    public void deletarComanda(Long id) {
        comandaRepository.deleteById(id);
    }

    public Comanda adicionarPedido(Long comandaId, Pedido pedido) {
        return comandaRepository.findById(comandaId)
                .map(comanda -> {
                    if (!comanda.isEstaFechada()) {
                        comanda.getLista_pedido().add(pedido);
                        comanda.setValor_total(comanda.getValor_total() + pedido.getValorFinal());
                        return comandaRepository.save(comanda);
                    } else {
                        throw new RuntimeException("A comanda já está fechada. Não é possível adicionar novos pedidos.");
                    }
                })
                .orElseThrow(() -> new RuntimeException("Comanda não encontrada"));
    }

    public Comanda fecharComanda(Long id) {
        return comandaRepository.findById(id)
                .map(comanda -> {
                    if (!comanda.isEstaFechada()) {
                        comanda.setEstaFechada(true);
                        return comandaRepository.save(comanda);
                    } else {
                        throw new RuntimeException("Comanda já está fechada.");
                    }
                })
                .orElseThrow(() -> new RuntimeException("Comanda não encontrada"));
    }

    public double calcularValorFinal(Long id) {
        return comandaRepository.findById(id)
                .map(comanda -> {
                    if (comanda.isEstaFechada()) {
                        return comanda.getValor_total() * Comanda.TAXA_SERVICO;
                    } else {
                        throw new RuntimeException("Comanda ainda não foi fechada.");
                    }
                })
                .orElseThrow(() -> new RuntimeException("Comanda não encontrada"));
    }
}

