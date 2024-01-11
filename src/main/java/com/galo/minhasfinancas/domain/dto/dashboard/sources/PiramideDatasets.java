package com.galo.minhasfinancas.domain.dto.dashboard.sources;

public class PiramideDatasets {

  private String type;
  private String backgroundColor;
  private String borderColor;
  private int[] data;

  public PiramideDatasets(String backgroundColor, String borderColor, int[] data) {
    this.backgroundColor = backgroundColor;
    this.borderColor = borderColor;
    this.data = data;
  }
  public String getType() {return type;}
  public void setType(String type) {this.type = type;}
  public String getBackgroundColor() {return backgroundColor;}
  public void setBackgroundColor(String backgroundColor) {this.backgroundColor = backgroundColor;}
  public String getBorderColor() {return borderColor;}
  public void setBorderColor(String borderColor) {this.borderColor = borderColor;}
  public int[] getData() {return data;}
  public void setData(int[] data) {this.data = data;}
}

