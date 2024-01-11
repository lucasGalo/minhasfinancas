package com.galo.minhasfinancas.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Unidade implements BaseEnum {

  @JsonProperty("Un") Un(1, "Un"),
  @JsonProperty("m") m(2, "m"),
  @JsonProperty("m²") mQuadrado(3, "m²"),
  @JsonProperty("Kg") Kg(4, "Kg");

  private final int cod;
  private final String value;

  Unidade(int cod, String value) {
    this.cod = cod;
    this.value = value;
  }
  @Override
  public int getCod() {
    return cod;
  }
  @Override
  public String getValue() {
    return value;
  }
}
