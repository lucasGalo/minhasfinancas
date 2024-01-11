package com.galo.minhasfinancas.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carteira")
public class Carteira extends EntityBase {

  @OneToMany(mappedBy = "carteira")
  private List<Pagamento> pagamentos = new ArrayList<>();
  public Carteira() {}

  public Carteira(Integer id, Boolean isAtivo, String nome, String descricao) {
    super(id, isAtivo, nome, descricao);
  }

  public List<Pagamento> getPagamentos() {return pagamentos;}
  public void setPagamentos(List<Pagamento> pagamentos) {this.pagamentos = pagamentos;}
}