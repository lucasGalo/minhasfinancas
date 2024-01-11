package com.galo.minhasfinancas.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Icone implements BaseEnum {

  @JsonProperty("balloon") balloon(1, "balloon-outline"),
  @JsonProperty("cash") cash(2, "cash-outline"),
  @JsonProperty("construct") construct(3, "construct-outline"),
  @JsonProperty("person") person(4, "person-outline"),
  @JsonProperty("pizza") pizza(5, "pizza-outline"),
  @JsonProperty("sunny") sunny(6, "sunny-outline"),
  @JsonProperty("wallet") wallet(7, "wallet-outline"),
  @JsonProperty("shirt") shirt(8, "shirt-outline"),
  @JsonProperty("cart") cart(9, "cart-outline"),
  @JsonProperty("card") card(10, "card-outline"),
  @JsonProperty("home") home(11, "home-outline"),
  @JsonProperty("fast-food") fast_food(12, "fast-food-outline"),
  @JsonProperty("barbell") barbell(13, "barbell-outline"),
  @JsonProperty("locate") locate(14, "locate-outline"),
  @JsonProperty("desktop") desktop(15, "desktop-outline"),
  @JsonProperty("paw") paw(16, "paw-outline"),
  @JsonProperty("diamond") diamond(17, "diamond-outline"),
  ;

  private final int cod;
  private final String value;

  Icone(int cod, String value) {
    this.cod = cod;
    this.value = value;
  }
  @Override
  public int getCod() {
    return cod;
  }
  @Override
  public String getValue() {
    return value;
  }
}
