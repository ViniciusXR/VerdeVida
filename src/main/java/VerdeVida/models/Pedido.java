package VerdeVida.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = Pedido.TABLE_NAME)
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Pedido {
    public static final String TABLE_NAME = "pedido";

    public static final double TAXA_SERVICO = 1.10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "quantidade")
    private int quantidade;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pedido_id")
    private List<Item> itens;

    @ManyToOne
    @JoinColumn(name = "mesa_id")
    @JsonBackReference
    private Mesa mesa;

    public double calcularTotal() {
        return itens.stream().mapToDouble(Item::getPreco).sum();
    }

    public double calcularTotalComTaxa() {
        return calcularTotal() * (TAXA_SERVICO);
    }
}
