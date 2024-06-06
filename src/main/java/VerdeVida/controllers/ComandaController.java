package VerdeVida.controllers;

import VerdeVida.models.Comanda;
import VerdeVida.models.Pedido;
import VerdeVida.services.ComandaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comandas")
public class ComandaController {

    @Autowired
    private ComandaService comandaService;

    @PostMapping
    public ResponseEntity<Comanda> criarComanda(@RequestBody Comanda comanda) {
        Comanda novaComanda = comandaService.criarComanda(comanda);
        return ResponseEntity.ok(novaComanda);
    }

    @GetMapping
    public ResponseEntity<List<Comanda>> listarComandas() {
        List<Comanda> comandas = comandaService.listarComandas();
        return ResponseEntity.ok(comandas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comanda> obterComandaPorId(@PathVariable Long id) {
        return comandaService.obterComandaPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comanda> atualizarComanda(@PathVariable Long id, @RequestBody Comanda comandaAtualizada) {
        try {
            Comanda comanda = comandaService.atualizarComanda(id, comandaAtualizada);
            return ResponseEntity.ok(comanda);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarComanda(@PathVariable Long id) {
        comandaService.deletarComanda(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/pedidos")
    public ResponseEntity<Comanda> adicionarPedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        try {
            Comanda comanda = comandaService.adicionarPedido(id, pedido);
            return ResponseEntity.ok(comanda);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/{id}/fechar")
    public ResponseEntity<Comanda> fecharComanda(@PathVariable Long id) {
        try {
            Comanda comanda = comandaService.fecharComanda(id);
            return ResponseEntity.ok(comanda);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}/valor-final")
    public ResponseEntity<Double> calcularValorFinal(@PathVariable Long id) {
        try {
            double valorFinal = comandaService.calcularValorFinal(id);
            return ResponseEntity.ok(valorFinal);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}

