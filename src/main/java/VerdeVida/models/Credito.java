package VerdeVida.models;

import java.time.LocalDate;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CREDITO")
public class Credito extends Pagamento {


    public Credito() {}

    public Credito(double valor, LocalDate dataPagamento, String bandeira) {
        super(valor, dataPagamento);
        super.setBandeira(bandeira);
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

