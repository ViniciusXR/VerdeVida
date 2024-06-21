package VerdeVida.models;

import java.time.LocalDate;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DEBITO")
public class Debito extends Pagamento {


    public Debito() {}

    public Debito(double valor, LocalDate dataPagamento, String bancoOrigem) {
        super(valor, dataPagamento);
        super.setBancoOrigem(bancoOrigem);
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

