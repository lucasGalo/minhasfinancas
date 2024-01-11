package com.galo.minhasfinancas.domain.querys;

public class UsuarioQuery extends QUERY {

  private String action;
  private String id;
  private String antiga;
  private String nova;
  private String descricao;

  public UsuarioQuery() {
  }

  public String getAction() {return action;}

  public void setAction(String action) {this.action = action;}
  public String getId() {return id;}
  public void setId(String id) {this.id = id;}
  public String getAntiga() {return antiga;}
  public void setAntiga(String antiga) {this.antiga = antiga;}
  public String getNova() {return nova;}
  public void setNova(String nova) {this.nova = nova;}
  public String getDescricao() {return descricao;}
  public void setDescricao(String descricao) {this.descricao = descricao;}
}
