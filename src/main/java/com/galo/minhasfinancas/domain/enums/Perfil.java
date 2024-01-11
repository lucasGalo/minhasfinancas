package com.galo.minhasfinancas.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Perfil implements BaseEnum {

    @JsonProperty("ADMIN") ADMIN(1, "ROLE_ADMIN"),
    @JsonProperty("FISCAL") FISCAL(2, "ROLE_FISCAL"),
    @JsonProperty("OPERADOR") OPERADOR(3, "ROLE_OPERADOR"),
    @JsonProperty("LAVADOR") LAVADOR(4, "ROLE_LAVADOR"),
    @JsonProperty("CLIENTE") CLIENTE(5, "ROLE_CLIENTE");

    private final int cod;
    private final String value;

    Perfil(int cod, String value) {
        this.cod = cod;
        this.value = value;
    }

    public int getCod() {
        return cod;
    }

    public String getValue() {
        return value;
    }

    public static Perfil toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (Perfil x : Perfil.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }

    public static String[] getPerfis(){
        List<String> collect = Arrays.stream(Perfil.values()).map(Perfil::getValue).collect(Collectors.toList());
        String s = collect.toString();
        return s.substring(1, s.length()-1).split(",");
    }

}
