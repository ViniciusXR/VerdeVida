package VerdeVida.controllers;

import VerdeVida.models.Credito;
import VerdeVida.models.Debito;
import VerdeVida.models.Dinheiro;
import VerdeVida.models.Pagamento;
import VerdeVida.models.Pix;
import VerdeVida.services.MesaService;
import VerdeVida.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private MesaService mesaService;

    @GetMapping
    public ResponseEntity<List<Pagamento>> getAllPagamentos() {
        List<Pagamento> pagamentos = pagamentoService.findAll();
        return ResponseEntity.ok(pagamentos);
    }

    // Endpoints para tipos especificos de Pagamento
    @PostMapping("/credito")
    public ResponseEntity<Credito> createCredito(@RequestBody Credito credito) {
        Credito savedCredito = (Credito) pagamentoService.saveCredito(credito);
        return ResponseEntity.ok(savedCredito);
    }

    @PostMapping("/debito")
    public ResponseEntity<Debito> createDebito(@RequestBody Debito debito) {
        Debito savedDebito = (Debito) pagamentoService.saveDebito(debito);
        return ResponseEntity.ok(savedDebito);
    }

    @PostMapping("/dinheiro")
    public ResponseEntity<Dinheiro> createDinheiro(@RequestBody Dinheiro dinheiro) {
        Dinheiro savedDinheiro = (Dinheiro) pagamentoService.saveDinheiro(dinheiro);
        return ResponseEntity.ok(savedDinheiro);
    }

    @PostMapping("/pix")
    public ResponseEntity<Pix> createPix(@RequestBody Pix pix) {
        Pix savedPix = (Pix) pagamentoService.savePix(pix);
        return ResponseEntity.ok(savedPix);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> getPagamentoById(@PathVariable Long id) {
        Optional<Pagamento> pagamento = pagamentoService.findById(id);
        return pagamento.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pagamento> createPagamento(@RequestBody Pagamento pagamento) {
        Pagamento savedPagamento = pagamentoService.save(pagamento);
        return ResponseEntity.ok(savedPagamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> updatePagamento(@PathVariable Long id, @RequestBody Pagamento pagamentoDetails) {
        Optional<Pagamento> optionalPagamento = pagamentoService.findById(id);
        if (optionalPagamento.isPresent()) {
            Pagamento pagamento = optionalPagamento.get();
            pagamento.setValor(pagamentoDetails.getValor());
            pagamento.setDataPagamento(pagamentoDetails.getDataPagamento());
          
            Pagamento updatedPagamento = pagamentoService.save(pagamento);
            return ResponseEntity.ok(updatedPagamento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePagamento(@PathVariable Long id) {
        if (pagamentoService.findById(id).isPresent()) {
            pagamentoService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{mesaId}/encerrar")
    public ResponseEntity<Double> encerrarPedidos(@PathVariable Long mesaId) {
        return ResponseEntity.ok(mesaService.encerrarPedidos(mesaId));
    }

    @GetMapping("/{mesaId}/dividirConta")
    public ResponseEntity<Double> dividirConta(@PathVariable Long mesaId) {
        return ResponseEntity.ok(mesaService.dividirConta(mesaId));
    }

    // Endpoint para calcular o total pago em um dia
    @GetMapping("/totalPagoEmUmDia")
    public ResponseEntity<Double> calcularTotalPagoEmUmDia(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        double total = pagamentoService.calcularTotalPagoEmUmDia(data);
        return ResponseEntity.ok(total);
    }
}
