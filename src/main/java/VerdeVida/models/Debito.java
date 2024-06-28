package VerdeVida.models;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "pagamentoId")
public class Debito extends Pagamento {

    private String bancoOrigem;

    public Debito() {}

    public Debito(double valor, LocalDate dataPagamento, String bancoOrigem) {
        super(valor, dataPagamento);
        this.bancoOrigem = bancoOrigem;
    }

    @Override
    public double calcularValorFinal() {
        return getValor() * 0.986; // 1.4% desconto
    }

    @Override
    public LocalDate calcularDataRecebimento() {
        return getDataPagamento().plusDays(14);
    }
}
