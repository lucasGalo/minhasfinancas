package com.galo.minhasfinancas.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum StatusRenda implements BaseEnum {

  @JsonProperty("RECEBIDA")  RECEBIDA(1, "RECEBIDA"),
  @JsonProperty("ARECEBER")  ARECEBER(2, "ARECEBER"),
  @JsonProperty("CANCELADA")  CANCELADA(3, "CANCELADA")
  ;

  private final int cod;
  private final String value;

  StatusRenda(int cod, String value) {
    this.cod = cod;
    this.value = value;
  }
  @Override public int getCod() {return cod; }
  @Override public String getValue() {return value;}
}
