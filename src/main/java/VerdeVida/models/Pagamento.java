package VerdeVida.models;


import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Inheritance
@DiscriminatorColumn(name = "tipo_pagamento")
@Getter
@Setter
@EqualsAndHashCode
public abstract class Pagamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private double valor;

    private String bandeira;

    private String bancoOrigem;

    private String nomeEmitente;
    
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
