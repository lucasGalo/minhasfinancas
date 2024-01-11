package com.galo.minhasfinancas.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Genero implements BaseEnum{

    @JsonProperty("FEMININO")
    FEMININO(1, "FEMININO"),
    @JsonProperty("MASCULINO")
    MASCULINO(2, "MASCULINO"),
    @JsonProperty("EMPRESA")
    EMPRESA(3, "EMPRESA");

    private final int cod;
    private final String value;

    Genero(int cod, String value) {
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
