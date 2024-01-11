package com.galo.minhasfinancas.domain.validacoes;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Documented
@Constraint(validatedBy = MoedaParse.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ParseMoeda {
  String message() default "Invalido valor de moeda";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
