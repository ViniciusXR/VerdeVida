package VerdeVida.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = Mesa.TABLE_NAME)
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Mesa {
    public static final String TABLE_NAME = "mesa";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "capacidade")
    private int capacidade;

    @Column(name = "estacheia")
    private boolean estaCheia;

    @OneToOne(mappedBy = "mesa")
    @JsonManagedReference
    private Requisicao requisicao;

    @OneToMany(mappedBy = "mesa")
    @JsonManagedReference
    private List<Pedido> pedidos;
}
