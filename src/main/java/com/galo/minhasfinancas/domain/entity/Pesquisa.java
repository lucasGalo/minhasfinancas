package com.galo.minhasfinancas.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pesquisa")
public class Pesquisa extends EntityBase {

  private String ip;
  public Pesquisa() {}
  public Pesquisa(Integer id, Boolean isAtivo, String nome, String ip) {
    super(id, isAtivo, nome, null);
    this.ip = ip;
  }

  public String getIp() {return ip;}
  public void setIp(String ip) {this.ip = ip;}
  @Override
  public String toString() {
    return "Pesquisa{" + "ip='" + ip + '\'' +
            '}';
  }
}
