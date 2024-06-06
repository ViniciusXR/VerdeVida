package VerdeVida.services;

import VerdeVida.models.Cardapio;
import VerdeVida.models.Item;
import VerdeVida.repositories.CardapioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardapioService {

    @Autowired
    private CardapioRepository cardapioRepository;

    public Cardapio criarCardapio(Cardapio cardapio) {
        return cardapioRepository.save(cardapio);
    }

    public List<Cardapio> listarCardapios() {
        return cardapioRepository.findAll();
    }

    public Cardapio adicionarItem(Long cardapioId, Item item) {
        Cardapio cardapio = cardapioRepository.findById(cardapioId)
                .orElseThrow(() -> new RuntimeException("Cardápio não encontrado"));
        cardapio.adicionarItem(item);
        return cardapioRepository.save(cardapio);
    }

    public Cardapio removerItem(Long cardapioId, Item item) {
        Cardapio cardapio = cardapioRepository.findById(cardapioId)
                .orElseThrow(() -> new RuntimeException("Cardápio não encontrado"));
        cardapio.removerItem(item);
        return cardapioRepository.save(cardapio);
    }
}

