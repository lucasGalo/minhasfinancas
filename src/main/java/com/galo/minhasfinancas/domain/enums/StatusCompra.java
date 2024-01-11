package com.galo.minhasfinancas.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum StatusCompra implements BaseEnum {

  @JsonProperty("CONCLUIDA")  CONCLUIDA(1, "CONCLUIDA"),
  @JsonProperty("PENDENTE")  PENDENTE(2, "PENDENTE"),
  @JsonProperty("CANCELADA")  CANCELADA(3, "CANCELADA"),
  @JsonProperty("FIXA")  FIXA(4, "FIXA")
  ;

  private final int cod;
  private final String value;

  StatusCompra(int cod, String value) {
    this.cod = cod;
    this.value = value;
  }
  @Override public int getCod() {return cod; }
  @Override public String getValue() {return value;}
}
