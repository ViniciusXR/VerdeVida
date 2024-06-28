package VerdeVida.models;

import java.time.LocalDate;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_pagamento")
@Getter
@Setter
@EqualsAndHashCode
public abstract class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double valor;

    private LocalDate dataPagamento;

    private LocalDate dataRecebimento;

    public Pagamento() {}

    public Pagamento(double valor, LocalDate dataPagamento) {
        this.valor = valor;
        this.dataPagamento = dataPagamento;
    }

    public abstract double calcularValorFinal();
    public abstract LocalDate calcularDataRecebimento();
}
