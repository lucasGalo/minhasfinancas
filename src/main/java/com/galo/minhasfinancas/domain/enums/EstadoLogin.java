package com.galo.minhasfinancas.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EstadoLogin implements BaseEnum {

    @JsonProperty("ONLINE")
    ONLINE(1, "ONLINE"),
    @JsonProperty("OFFLINE")
    OFFLLINE(2, "OFFLINE")
    ;

    private final int cod;
    private final String value;

    EstadoLogin(int cod, String value) {
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
