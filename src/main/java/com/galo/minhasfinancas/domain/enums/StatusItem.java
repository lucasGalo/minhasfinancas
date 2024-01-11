package com.galo.minhasfinancas.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum StatusItem implements BaseEnum {

  @JsonProperty("EDICAO")  EDICAO(1, "EDICAO"),
  @JsonProperty("LAVANDO")  LAVANDO(2, "LAVANDO"),
  @JsonProperty("CANCELADO")  CANCELADO(3, "CANCELADO"),
  @JsonProperty("PRONTO_RETIRADA")  PRONTO_RETIRADA(4, "PRONTO_RETIRADA"),
  @JsonProperty("CONCLUIDO")  CONCLUIDO(5, "CONCLUIDO"),
  @JsonProperty("AGENDADO")  AGENDADO(6, "AGENDADO");

  private final int cod;
  private final String value;

  StatusItem(int cod, String value) {
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
