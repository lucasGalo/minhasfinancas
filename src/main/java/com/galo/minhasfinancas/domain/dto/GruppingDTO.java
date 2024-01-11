package com.galo.minhasfinancas.domain.dto;

import com.galo.minhasfinancas.domain.enums.Icone;
import com.galo.minhasfinancas.domain.enums.Mes;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static com.galo.minhasfinancas.sources.DateUtil.toFormate;

public class GruppingDTO extends DTO {

  private String status;
  private double vlr;
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
  private Date date;
  private String categoria;
  private Icone icone;
  private String cor;
  private String mae;

  public GruppingDTO() {}

  public GruppingDTO(String nome, String status, double vlr, Date date, String categoria, Icone icone, String cor, String mae) {
    setNome(nome);
    this.status = status;
    this.vlr = vlr;
    this.date = date;
    this.categoria = categoria;
    this.icone = icone;
    this.cor = cor;
    this.mae = mae;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public double getVlr() {
    return vlr;
  }

  public void setVlr(double vlr) {
    this.vlr = vlr;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public Icone getIcone() {
    return icone;
  }

  public void setIcone(Icone icone) {
    this.icone = icone;
  }

  public String getCor() {
    return cor;
  }

  public void setCor(String cor) {
    this.cor = cor;
  }

  public String getMae() {
    return mae;
  }

  public void setMae(String mae) {
    this.mae = mae;
  }
}
