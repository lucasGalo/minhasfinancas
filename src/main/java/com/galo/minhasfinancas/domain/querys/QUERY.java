package com.galo.minhasfinancas.domain.querys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

public abstract class QUERY implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    protected ObjectMapper mapper = new ObjectMapper();

    public abstract String getAction();

}
