package VerdeVida.models;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "pagamentoId")
public class Pix extends Pagamento {

    private String nomeEmitente;

    public Pix() {}

    public Pix(double valor, LocalDate dataPagamento, String nomeEmitente) {
        super(valor, dataPagamento);
        this.nomeEmitente = nomeEmitente;
    }

    @Override
    public double calcularValorFinal() {
        double desconto = getValor() * 0.0145;
        return getValor() - Math.min(desconto, 10);
    }

    @Override
    public LocalDate calcularDataRecebimento() {
        return getDataPagamento();
    }
}
