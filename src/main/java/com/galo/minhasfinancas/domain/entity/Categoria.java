package com.galo.minhasfinancas.domain.entity;

import com.galo.minhasfinancas.domain.enums.Icone;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categoria")
public class Categoria extends EntityBase {

  @Enumerated(EnumType.STRING)
  @Column(name = "icon", nullable = false)
  private Icone icone;

  private String cor;

  @OneToMany(mappedBy = "categoria")
  private List<Compra> compras = new ArrayList<>();


  public Categoria() {}
  public Categoria(Integer id, Boolean isAtivo, String nome, String descricao, Icone icone, String cor) {
    super(id, isAtivo, nome, descricao);
    this.icone = icone;
    this.cor = cor;
  }

  public Icone getIcone() {return icone;}
  public void setIcone(Icone icone) {this.icone = icone;}
  public String getCor() {return cor;}
  public void setCor(String cor) {this.cor = cor;}
  public List<Compra> getCompras() {return compras;}
  public void setCompras(List<Compra> compras) {this.compras = compras;}
}
