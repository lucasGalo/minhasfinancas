package com.galo.minhasfinancas.domain.dto.dashboard.donut;

public class Data {

  private int[] data;
  private String[] backgroundColor;

  public Data() {
  }

  public Data(int[] data, String[] backgroundColor) {
    this.data = data;
    this.backgroundColor = backgroundColor;
  }

  public int[] getData() {
    return data;
  }

  public void setData(int[] data) {
    this.data = data;
  }

  public String[] getBackgroundColor() {
    return backgroundColor;
  }

  public void setBackgroundColor(String[] backgroundColor) {
    this.backgroundColor = backgroundColor;
  }
}
