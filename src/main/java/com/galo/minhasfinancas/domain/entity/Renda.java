package com.galo.minhasfinancas.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.galo.minhasfinancas.domain.enums.StatusRenda;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "renda")
public class Renda extends EntityBase {

    @Enumerated(EnumType.STRING)
    private StatusRenda statusRenda;
    private double vlr;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @JsonFormat(pattern = DD_MM_YYYY_HH_MM_SS)
    @Column(name = "date")
    private Date date;
    @OneToOne
    @JoinColumn(name = "tipo_id", nullable = false)
    private Tipo tipo;

    public Renda() {}
    public Renda(Integer id, Boolean isAtivo, String nome, StatusRenda renda, double vlr, String descricao, Usuario usuario, Tipo tipo) {
        super(id, isAtivo, nome, descricao);
        this.statusRenda = renda;
        this.vlr = vlr;
        this.usuario = usuario;
        this.tipo = tipo;
    }

    public StatusRenda getStatusRenda() {return statusRenda;}
    public void setStatusRenda(StatusRenda statusRenda) {this.statusRenda = statusRenda;}
    public double getVlr() {return vlr;}
    public void setVlr(double vlr) {this.vlr = vlr;}
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Date getDate() {return date;}
    public void setDate(Date date) {this.date = date;}
    public Tipo getTipo() {return tipo;}
    public void setTipo(Tipo tipo) {this.tipo = tipo;}
}
