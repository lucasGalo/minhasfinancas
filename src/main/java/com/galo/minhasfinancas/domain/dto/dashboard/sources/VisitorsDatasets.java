package com.galo.minhasfinancas.domain.dto.dashboard.sources;

public class VisitorsDatasets {

  private String type;
  private String backgroundColor;
  private String borderColor;
  private boolean fill;
  private String pointBorderColor;
  private String pointBackgroundColor;
  private int[] data;

  public VisitorsDatasets(String type,int[] data, String backgroundColor, String borderColor, String pointBorderColor, String pointBackgroundColor, boolean fill) {
    this.type = type;
    this.backgroundColor = backgroundColor;
    this.borderColor = borderColor;
    this.fill = fill;
    this.pointBorderColor = pointBorderColor;
    this.pointBackgroundColor = pointBackgroundColor;
    this.data = data;
  }
  public String getType() {return type;}
  public void setType(String type) {this.type = type;}
  public String getBackgroundColor() {return backgroundColor;}
  public void setBackgroundColor(String backgroundColor) {this.backgroundColor = backgroundColor;}
  public String getBorderColor() {return borderColor;}
  public void setBorderColor(String borderColor) {this.borderColor = borderColor;}
  public boolean isFill() {return fill;}
  public void setFill(boolean fill) {this.fill = fill;}
  public String getPointBorderColor() {return pointBorderColor;}
  public void setPointBorderColor(String pointBorderColor) {this.pointBorderColor = pointBorderColor;}
  public String getPointBackgroundColor() {return pointBackgroundColor;}
  public void setPointBackgroundColor(String pointBackgroundColor) {this.pointBackgroundColor = pointBackgroundColor;}
  public int[] getData() {return data;}
  public void setData(int[] data) {this.data = data;}
}

