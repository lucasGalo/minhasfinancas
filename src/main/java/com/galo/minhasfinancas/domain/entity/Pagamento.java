package com.galo.minhasfinancas.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "pagamento")
public class Pagamento extends EntityBase {

  @Column(name = "valor", nullable = false)
  private double vlr;
  @JsonFormat(pattern = DD_MM_YYYY)
  @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
  @Column(name = "data_pagamento", nullable = false)
  private Date dtPagamento;
  @OneToOne
  @JoinColumn(name = "compra_id", nullable = false)
  private Compra compra;
  @OneToOne
  @JoinColumn(name = "carteira_id", nullable = false)
  private Carteira carteira;

  public Pagamento() {}

  public Pagamento(Integer id, Boolean isAtivo, String nome, double vlr, String descricao, Date dtPagamento, Compra compra, Carteira carteira) {
    super(id, isAtivo, nome, descricao);
    this.vlr = vlr;
    this.dtPagamento = dtPagamento;
    this.compra = compra;
    this.carteira = carteira;
  }
  public double getVlr() {return vlr;}
  public void setVlr(double vlr) {
    this.vlr = vlr;
  }
  public Date getDtPagamento() {
    return dtPagamento;
  }
  public void setDtPagamento(Date dtPagamento) {
    this.dtPagamento = dtPagamento;
  }
  public Compra getCompra() {return compra;}
  public void setCompra(Compra compra) {this.compra = compra;}
  public Carteira getCarteira() {return carteira;}
  public void setCarteira(Carteira carteira) {this.carteira = carteira;}
}
