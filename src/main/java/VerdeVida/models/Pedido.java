package VerdeVida.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = Pedido.TABLE_NAME)
@Getter
@Setter
@EqualsAndHashCode
public class Pedido {
    public static final String TABLE_NAME = "pedido";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;
    @Column(name = "quantidade")
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "item")
    private Item item;

    @Column(name = "tipoPagamento")
    private String tipoPagamento;

    @Column(name = "detalhePagamento")
    private String detalhePagamento;

    @Column(name = "valorFinal")
    private double valorFinal;

    public Pedido() {
    }

    public Pedido(Item item, int quantidade, String tipoPagamento, String detalhePagamento) {
        this.item = item;
        this.quantidade = quantidade;
        this.tipoPagamento = tipoPagamento;
        this.detalhePagamento = detalhePagamento;
        this.valorFinal = item.getPreco() * quantidade;
    }
}
