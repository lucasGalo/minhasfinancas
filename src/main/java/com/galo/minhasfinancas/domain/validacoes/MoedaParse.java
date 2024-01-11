package com.galo.minhasfinancas.domain.validacoes;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class MoedaParse implements ConstraintValidator<ParseMoeda, String> {


  @Override
  public void initialize(ParseMoeda parseMoeda) {

  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    return false;
  }

}
