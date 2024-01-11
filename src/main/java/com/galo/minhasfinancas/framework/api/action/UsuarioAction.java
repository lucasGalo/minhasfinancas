package com.galo.minhasfinancas.framework.api.action;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.galo.minhasfinancas.core.services.usuario.IUsuarioServices;
import com.galo.minhasfinancas.domain.enums.BaseEnum;
import com.galo.minhasfinancas.domain.querys.UsuarioQuery;

public enum UsuarioAction implements BaseEnum, ActionEnum<UsuarioQuery, IUsuarioServices> {

  @JsonProperty("trocasenha") TROCASENHA (1, "trocasenha") { @Override public Boolean execute(UsuarioQuery query, IUsuarioServices services) {return services.trocaSenha(query);}},
  @JsonProperty("anotacoes") ANOTACOES (2, "anotacoes") { @Override public Boolean execute(UsuarioQuery query, IUsuarioServices services) {return services.anotacoes(query);}}
  ;

  private final int cod;
  private final String value;

  UsuarioAction(int cod, String value) {
    this.cod = cod;
    this.value = value;
  }
  public int getCod() {return cod;}
  public String getValue() {return value;}
}
