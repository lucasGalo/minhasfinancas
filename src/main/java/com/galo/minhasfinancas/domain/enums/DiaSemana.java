package com.galo.minhasfinancas.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DiaSemana implements BaseEnum {

    @JsonProperty("DOMINGO") DOMINGO(1, "DOMINGO"),
    @JsonProperty("SEGUNDA") SEGUNDA(2, "SEGUNDA"),
    @JsonProperty("TERCA") TERCA(3, "TERCA"),
    @JsonProperty("QUARTA") QUARTA(4, "QUARTA"),
    @JsonProperty("QUINTA") QUINTA(5, "QUINTA"),
    @JsonProperty("SEXTA") SEXTA(6,"SEXTA"),
    @JsonProperty("SABADO") SABADO(7,"SABADO")
    ;

    private final int cod;
    private final String value;

    DiaSemana(int cod, String value) {
        this.cod = cod;
        this.value = value;
    }

    public int getCod() {
        return cod;
    }

    public String getValue() {
        return value;
    }
}
