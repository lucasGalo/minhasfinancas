package com.galo.minhasfinancas.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item")
public class Item extends EntityBase {
    private String cod;
    private double qtd;
    private double vlrUni;
    private double vlrTotal;
    @ManyToOne
    @JoinColumn(name = "compra_id", nullable = false)
    private Compra compra;

    public Item() {}
    public Item(Integer id, Boolean isAtivo, String nome, String descricao, String cod, double qtd, double vlrUni, double vlrTotal, Compra compra) {
        super(id, isAtivo, nome, descricao);
        this.cod = cod;
        this.qtd = qtd;
        this.vlrUni = vlrUni;
        this.vlrTotal = vlrTotal;
        this.compra = compra;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getQtd() {
        return qtd;
    }

    public void setQtd(double qtd) {
        this.qtd = qtd;
    }

    public double getVlrUni() {
        return vlrUni;
    }

    public void setVlrUni(double vlrUni) {
        this.vlrUni = vlrUni;
    }

    public double getVlrTotal() {
        return vlrTotal;
    }

    public void setVlrTotal(double vlrTotal) {
        this.vlrTotal = vlrTotal;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }
}
