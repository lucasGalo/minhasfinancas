package com.galo.minhasfinancas.domain.enums;

public enum ColorEnum {

    PRIMARY("primary"),
    INFO("info"),
    SUCCESS("success"),
    WARNING("warning"),
    DANGER("danger");

    private final String name;

    ColorEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

