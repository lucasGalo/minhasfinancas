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
@Table(name = "tipo")
public class Tipo extends EntityBase {

  @Enumerated(EnumType.STRING)
  @Column(name = "icon", nullable = false)
  private Icone icone;

  private String cor;

  @OneToMany(mappedBy = "tipo")
  private List<Renda> rendas = new ArrayList<>();


  public Tipo() {}
  public Tipo(Integer id, Boolean isAtivo, String nome, String descricao, Icone icone, String cor) {
    super(id, isAtivo, nome, descricao);
    this.icone = icone;
    this.cor = cor;
  }
  public Icone getIcone() {return icone;}
  public void setIcone(Icone icone) {this.icone = icone;}
  public String getCor() {return cor;}
  public void setCor(String cor) {this.cor = cor;}
  public List<Renda> getRendas() {return rendas;}
  public void setRendas(List<Renda> rendas) {this.rendas = rendas;}

}
