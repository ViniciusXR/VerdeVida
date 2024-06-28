package VerdeVida.models;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "pagamentoId")
public class Dinheiro extends Pagamento {

    public Dinheiro() {}

    public Dinheiro(double valor, LocalDate dataPagamento) {
        super(valor, dataPagamento);
    }

    @Override
    public double calcularValorFinal() {
        return getValor();
    }

    @Override
    public LocalDate calcularDataRecebimento() {
        return getDataPagamento();
    }
}
