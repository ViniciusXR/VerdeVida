package VerdeVida.controllers;

import VerdeVida.models.FilaEspera;
import VerdeVida.models.Requisicao;
import VerdeVida.services.FilaEsperaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filaespera")
public class FilaEsperaController {

    @Autowired
    private FilaEsperaService filaEsperaService;

    @PostMapping
    public ResponseEntity<FilaEspera> criarFilaEspera(@RequestBody FilaEspera filaEspera) {
        FilaEspera novaFilaEspera = filaEsperaService.criarFilaEspera(filaEspera);
        return ResponseEntity.ok(novaFilaEspera);
    }

    @GetMapping
    public ResponseEntity<List<FilaEspera>> listarFilasEspera() {
        List<FilaEspera> filasEspera = filaEsperaService.listarFilasEspera();
        return ResponseEntity.ok(filasEspera);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilaEspera> obterFilaEsperaPorId(@PathVariable Long id) {
        return filaEsperaService.obterFilaEsperaPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilaEspera> atualizarFilaEspera(@PathVariable Long id, @RequestBody FilaEspera filaEsperaAtualizada) {
        try {
            FilaEspera filaEspera = filaEsperaService.atualizarFilaEspera(id, filaEsperaAtualizada);
            return ResponseEntity.ok(filaEspera);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFilaEspera(@PathVariable Long id) {
        filaEsperaService.deletarFilaEspera(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/requisicoes")
    public ResponseEntity<FilaEspera> adicionarRequisicao(@PathVariable Long id, @RequestBody Requisicao requisicao) {
        try {
            FilaEspera filaEspera = filaEsperaService.adicionarRequisicao(id, requisicao);
            return ResponseEntity.ok(filaEspera);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}/requisicoes")
    public ResponseEntity<FilaEspera> removerRequisicao(@PathVariable Long id) {
        try {
            FilaEspera filaEspera = filaEsperaService.removerRequisicao(id);
            return ResponseEntity.ok(filaEspera);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}/proxima-requisicao")
    public ResponseEntity<Requisicao> obterProximaRequisicao(@PathVariable Long id) {
        try {
            Requisicao requisicao = filaEsperaService.obterProximaRequisicao(id);
            return ResponseEntity.ok(requisicao);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
