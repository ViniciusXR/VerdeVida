package VerdeVida.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import VerdeVida.models.Mesa;
import VerdeVida.services.MesaService;

@RestController
@RequestMapping("/mesas")
public class MesaController {

    @Autowired
    private MesaService mesaService;

    @PostMapping
    public ResponseEntity<Mesa> criarMesa(@RequestBody Mesa mesa) {
        return ResponseEntity.ok(mesaService.criarMesa(mesa));
    }

    @GetMapping
    public ResponseEntity<List<Mesa>> listarMesas() {
        return ResponseEntity.ok(mesaService.listarMesas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mesa> obterMesaPorId(@PathVariable Long id) {
        return mesaService.obterMesaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mesa> atualizarMesa(@PathVariable Long id, @RequestBody Mesa mesa) {
        try {
            return ResponseEntity.ok(mesaService.atualizarMesa(id, mesa));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMesa(@PathVariable Long id) {
        mesaService.deletarMesa(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{mesaId}/pedidos")
    public ResponseEntity<Mesa> adicionarPedido(@PathVariable Long mesaId, @RequestBody Map<String, Object> payload) {
        int quantidade = (int) payload.get("quantidade");
        List<Integer> itensIds = (List<Integer>) payload.get("itensIds");

        Mesa mesa = mesaService.adicionarPedido(mesaId, quantidade, itensIds);

        return new ResponseEntity<>(mesa, HttpStatus.OK);
    }

    @PostMapping("/{mesaId}/encerrar")
    public ResponseEntity<Double> encerrarPedidos(@PathVariable Long mesaId) {
        return ResponseEntity.ok(mesaService.encerrarPedidos(mesaId));
    }

    @GetMapping("/{mesaId}/dividirConta")
    public ResponseEntity<Double> dividirConta(@PathVariable Long mesaId) {
        return ResponseEntity.ok(mesaService.dividirConta(mesaId));
    }
}
