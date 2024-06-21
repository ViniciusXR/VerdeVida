package VerdeVida.models;

import java.time.LocalDate;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PIX")
public class Pix extends Pagamento {

    

    public Pix() {}

    public Pix(double valor, LocalDate dataPagamento, String nomeEmitente) {
        super(valor, dataPagamento);
        super.setNomeEmitente(nomeEmitente);
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

