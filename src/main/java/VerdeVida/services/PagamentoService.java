package VerdeVida.services;

import VerdeVida.models.Credito;
import VerdeVida.models.Debito;
import VerdeVida.models.Dinheiro;
import VerdeVida.models.Pagamento;
import VerdeVida.models.Pix;
import VerdeVida.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public List<Pagamento> findAll() {
        return pagamentoRepository.findAll();
    }

    public Optional<Pagamento> findById(Long id) {
        return pagamentoRepository.findById(id);
    }

    public Pagamento save(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    public Pagamento saveCredito(Credito pagamento) {
        pagamento.setDataRecebimento(pagamento.getDataPagamento().plusDays(30));
        pagamento.setValor((pagamento.getValor() * 0.969));
        return pagamentoRepository.save(pagamento);
    }

    public Pagamento saveDebito(Debito pagamento) {
        pagamento.setDataRecebimento(pagamento.getDataPagamento().plusDays(14));
        pagamento.setValor((pagamento.getValor() * 0.986));
        return pagamentoRepository.save(pagamento);
    }

    public Pagamento saveDinheiro(Dinheiro pagamento) {
        pagamento.setDataRecebimento(pagamento.getDataPagamento());
        return pagamentoRepository.save(pagamento);
    }

    public Pagamento savePix(Pix pagamento) {
        pagamento.setDataRecebimento(pagamento.getDataPagamento());
        double desconto = pagamento.getValor() * 0.0145;
        pagamento.setValor(pagamento.getValor() - Math.min(desconto, 10));
        return pagamentoRepository.save(pagamento);
    }


    public void deleteById(Long id) {
        pagamentoRepository.deleteById(id);
    }

    public double calcularTotalPagoEmUmDia(LocalDate dataPagamento) {
        List<Pagamento> pagamentos = pagamentoRepository.findByDataPagamento(dataPagamento);
        return pagamentos.stream().mapToDouble(Pagamento::getValor).sum();
    }
}
