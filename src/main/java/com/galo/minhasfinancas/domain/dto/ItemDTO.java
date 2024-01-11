package com.galo.minhasfinancas.domain.dto;

import com.galo.minhasfinancas.domain.entity.Compra;
import com.galo.minhasfinancas.domain.entity.Item;

import java.util.Objects;

import static com.galo.minhasfinancas.reflectionCuston.ReflexaoCustom.custom;
import static com.galo.minhasfinancas.sources.MoedaUtil.parseDouble;

public class ItemDTO extends DTO {
  private String cod;
  private double qtd;
  private String vlrUni;
  private String vlrTotal;
  private CompraDTO compra;

  public ItemDTO() {
  }

  public ItemDTO(Item obj, String classe) {
    if (null != obj) {
      setDefault(obj);
      this.cod = obj.getCod();
      this.qtd = obj.getQtd();
      this.vlrUni = Double.toString(obj.getVlrUni());
      this.vlrTotal = Double.toString(obj.getVlrTotal());
      if (!Objects.equals(classe, Compra.class.getSimpleName()))
        this.compra = custom(obj.getCompra(), CompraDTO.class, Item.class);
    }
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
    return parseDouble(vlrUni);
  }
  public void setVlrUni(String vlrUni) {
    this.vlrUni = vlrUni;
  }
  public double getVlrTotal() { return  parseDouble(vlrTotal);}
  public void setVlrTotal(String vlrTotal) {
    this.vlrTotal = vlrTotal;
  }
  public CompraDTO getCompra() {
    return compra;
  }
  public void setCompra(CompraDTO compra) {this.compra = compra;}
}
