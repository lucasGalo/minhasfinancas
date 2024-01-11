package com.galo.minhasfinancas.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Ano implements BaseEnum {

    @JsonProperty("_2023")
    _2023(2023, "_2023"),
    ;

    private final int cod;
    private final String value;

    private Ano(int cod, String value) {
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
