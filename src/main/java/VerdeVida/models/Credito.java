package VerdeVida.models;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "pagamentoId")
public class Credito extends Pagamento {

    private String bandeira;

    public Credito() {}

    public Credito(double valor, LocalDate dataPagamento, String bandeira) {
        super(valor, dataPagamento);
        this.bandeira = bandeira;
    }

    @Override
    public double calcularValorFinal() {
        return getValor() * 0.969; // 3.1% desconto
    }

    @Override
    public LocalDate calcularDataRecebimento() {
        return getDataPagamento().plusDays(30);
    }
}
