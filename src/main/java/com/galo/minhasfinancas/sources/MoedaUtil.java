package com.galo.minhasfinancas.sources;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoedaUtil {

  public static double parseDouble(String valor) {
    if(valor != null && !valor.isEmpty()) {
      BigDecimal big = new BigDecimal(valor.trim().replaceAll(",", "."));
      return big.setScale(2, RoundingMode.HALF_DOWN).doubleValue();
    }
    return 0.00;
  }

  public static double arrendondamento(double d){
    BigDecimal big = new BigDecimal(d);
    return big.setScale(2, RoundingMode.HALF_DOWN).doubleValue();
  }
}
