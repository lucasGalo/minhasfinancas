package com.galo.minhasfinancas.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.galo.minhasfinancas.domain.enums.StatusCompra;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "compra")
public class Compra extends EntityBase {
    @Enumerated(EnumType.STRING)
    private StatusCompra statusCompra;
    private double vlr;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @OneToMany(mappedBy = "compra")
    private List<Item> items = new ArrayList<>();
    @OneToMany(mappedBy = "compra")
    private List<Pagamento> pagamentos = new ArrayList<>();
    @JsonFormat(pattern = DD_MM_YYYY_HH_MM_SS)
    @Column(name = "date")
    private Date date;
    @OneToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    public Compra() {}
    public Compra(Integer id, Boolean isAtivo, String nome, StatusCompra statusCompra, double vlr, String descricao, Usuario usuario, List<Item> items, List<Pagamento> pagamentos, Categoria categoria) {
        super(id, isAtivo, nome, descricao);
        this.vlr = vlr;
        this.statusCompra = statusCompra;
        this.usuario = usuario;
        this.items = items;
        this.pagamentos = pagamentos;
        this.categoria = categoria;
    }

    public double getVlr() {return vlr;}
    public void setVlr(double vlr) {this.vlr = vlr;}
    public StatusCompra getStatusCompra() {return statusCompra;}
    public void setStatusCompra(StatusCompra statusCompra) {this.statusCompra = statusCompra;}
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Date getDate() {return date;}
    public void setDate(Date date) {this.date = date;}
    public List<Item> getItems() {return items;}
    public void setItems(List<Item> itens) {this.items = itens;}
    public List<Pagamento> getPagamentos() {return pagamentos;}
    public void setPagamentos(List<Pagamento> pagamentos) {this.pagamentos = pagamentos;}
    public Categoria getCategoria() {return categoria;}
    public void setCategoria(Categoria categoria) {this.categoria = categoria;}
}
