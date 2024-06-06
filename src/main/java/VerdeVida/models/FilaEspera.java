package VerdeVida.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = FilaEspera.TABLE_NAME)
public class FilaEspera {
    public static final String TABLE_NAME = "filaespera";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Requisicao> requisicoes = new ArrayList<>();

    private int tamanho;

    public FilaEspera() {
    }

    public FilaEspera(Long id, List<Requisicao> requisicoes, int tamanho) {
        this.id = id;
        this.requisicoes = requisicoes;
        this.tamanho = tamanho;
    }

    public Long getId() {
        return id;
    }

    public List<Requisicao> getRequisicoes() {
        return requisicoes;
    }

    public void setRequisicoes(List<Requisicao> requisicoes) {
        this.requisicoes = requisicoes;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public void adicionar(Requisicao requisicao) {
        if (tamanho < 10) {
            requisicoes.add(requisicao);
            tamanho++;
        } else {
            throw new RuntimeException("A fila está cheia.");
        }
    }

    public void remover() {
        if (tamanho > 0) {
            requisicoes.remove(0);
            tamanho--;
        } else {
            throw new RuntimeException("A fila está vazia.");
        }
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public boolean estaCheia() {
        return tamanho == 10;
    }

    public Requisicao getProxima() {
        if (!estaVazia()) {
            return requisicoes.get(0);
        }
        return null;
    }
}
