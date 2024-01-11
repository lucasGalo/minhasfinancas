package com.galo.minhasfinancas.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "img")
public class Img extends EntityBase {

  private String url;
  public Img() {}

  public Img(Integer id, Boolean isAtivo, String nome, String url) {
    super(id, isAtivo, nome,null);
    this.url = url;
  }

  public String getUrl() {return url;}
  public void setUrl(String url) {this.url = url;}
  @Override
  public String toString() {
    return "Img{" + "url='" + url + '\'' +
            '}';
  }
}
