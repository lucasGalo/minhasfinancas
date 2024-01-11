package com.galo.minhasfinancas.exceptions.exceptions.adpters;

import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class FieldErrorAdpter {

    public static List<FieldError> listErrorAdater(Exception e, String name) {
        final String standTexto = e.getMessage();
        List<FieldError> erros = new ArrayList<>();
        erros.add(new FieldError(name, "", standTexto));
        return erros;
    }
}
