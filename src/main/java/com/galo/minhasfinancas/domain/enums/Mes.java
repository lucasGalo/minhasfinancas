package com.galo.minhasfinancas.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Mes implements BaseEnum {

    @JsonProperty("JANEIRO")
    JANEIRO(1, "JANEIRO"),
    @JsonProperty("FEVEREIRO")
    FEVEREIRO(2, "FEVEREIRO"),
    @JsonProperty("MARCO")
    MARCO(3, "MARCO"),
    @JsonProperty("ABRIL")
    ABRIL(4, "ABRIL"),
    @JsonProperty("MAIO")
    MAIO(5, "MAIO"),
    @JsonProperty("JUNHO")
    JUNHO(6, "JUNHO"),
    @JsonProperty("JULHO")
    JULHO(7, "JULHO"),
    @JsonProperty("AGOSTO")
    AGOSTO(8, "AGOSTO"),
    @JsonProperty("SETEMBRO")
    SETEMBRO(9, "SETEMBRO"),
    @JsonProperty("OUTUBRO")
    OUTUBRO(10, "OUTUBRO"),
    @JsonProperty("NOVEMBRO")
    NOVEMBRO(11, "NOVEMBRO"),
    @JsonProperty("DEZEMBRO")
    DEZEMBRO(12, "DEZEMBRO"),
    ;

    private final int cod;
    private final String value;

    Mes(int cod, String value) {
        this.cod = cod;
        this.value = value;
    }

    public int getCod() {
        return cod;
    }

    public String getValue() {
        return value;
    }

    public static Mes toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (Mes x : Mes.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }

}
