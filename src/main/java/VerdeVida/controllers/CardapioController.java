package VerdeVida.controllers;

import VerdeVida.models.Cardapio;
import VerdeVida.models.Item;
import VerdeVida.services.CardapioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cardapios")
public class CardapioController {

    @Autowired
    private CardapioService cardapioService;

    @PostMapping
    public Cardapio criarCardapio(@RequestBody Cardapio cardapio) {
        return cardapioService.criarCardapio(cardapio);
    }

    @GetMapping
    public List<Cardapio> listarCardapios() {
        return cardapioService.listarCardapios();
    }

    @PostMapping("/{cardapioId}/itens")
    public Cardapio adicionarItem(@PathVariable Long cardapioId, @RequestBody Item item) {
        return cardapioService.adicionarItem(cardapioId, item);
    }

    @DeleteMapping("/{cardapioId}/itens")
    public Cardapio removerItem(@PathVariable Long cardapioId, @RequestBody Item item) {
        return cardapioService.removerItem(cardapioId, item);
    }
}

