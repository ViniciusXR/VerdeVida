package VerdeVida.controllers;

import VerdeVida.models.Requisicao;
import VerdeVida.services.RequisicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requisicoes")
public class RequisicaoController {

    @Autowired
    private RequisicaoService requisicaoService;

    @PostMapping
    public ResponseEntity<Requisicao> criarRequisicao(@RequestBody Requisicao requisicao) {
        Requisicao novaRequisicao = requisicaoService.criarRequisicao(requisicao);
        requisicaoService.alocarRequisicao(requisicao);
        return ResponseEntity.ok(novaRequisicao);
    }

    @GetMapping
    public ResponseEntity<List<Requisicao>> listarRequisicoes() {
        List<Requisicao> requisicoes = requisicaoService.listarRequisicoes();
        return ResponseEntity.ok(requisicoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Requisicao> obterRequisicaoPorId(@PathVariable Long id) {
        return requisicaoService.obterRequisicaoPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Requisicao> atualizarRequisicao(@PathVariable Long id, @RequestBody Requisicao requisicaoAtualizada) {
        try {
            Requisicao requisicao = requisicaoService.atualizarRequisicao(id, requisicaoAtualizada);
            return ResponseEntity.ok(requisicao);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRequisicao(@PathVariable Long id) {
        requisicaoService.deletarRequisicao(id);
        return ResponseEntity.noContent().build();
    }
}
