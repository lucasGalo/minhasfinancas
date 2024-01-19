package com.galo.minhasfinancas.domain.dto.dashboard.donut;

public class DonutData {

  private String label;
  private int data;
  private String color;
  public DonutData() {}

  public DonutData(String label, int data, String color) {
    this.label = label;
    this.data = data;
    this.color = color;
  }

  public String getLabel() {return label;}
  public void setLabel(String label) {this.label = label;}
  public int getData() {return data;}
  public void setData(int data) {this.data = data;}
  public String getColor() {return color;}
  public void setColor(String color) {this.color = color;}
}
