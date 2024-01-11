package com.galo.minhasfinancas.sources;

public class RandomColor {
  public static String getRandomColor() {
    String[] letters = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
    StringBuilder color = new StringBuilder("#");
    for (int i = 0; i < 6; i++ ) {
      color.append(letters[(int) Math.round(Math.random() * 15)]);
    }
    return color.toString();
  }
}
