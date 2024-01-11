package com.galo.minhasfinancas.domain.dto.dashboard.sources;

import java.util.Arrays;

public class Datasets {

  private String label;
  private String backgroundColor;
  private String borderColor;
  private boolean pointRadius;
  private String pointColor;
  private String pointStrokeColor;
  private String pointHighlightFill;
  private String pointHighlightStroke;
  private int[] data;

  public Datasets(String label, String backgroundColor, String borderColor, boolean pointRadius, String pointColor, String pointStrokeColor, String pointHighlightFill, String pointHighlightStroke,
                  int[] data) {
    this.label = label;
    this.backgroundColor = backgroundColor;
    this.borderColor = borderColor;
    this.pointRadius = pointRadius;
    this.pointColor = pointColor;
    this.pointStrokeColor = pointStrokeColor;
    this.pointHighlightFill = pointHighlightFill;
    this.pointHighlightStroke = pointHighlightStroke;
    this.data = data;
  }

  public Datasets() {}
  public String getLabel() {
    return label;
  }
  public void setLabel(String label) {
    this.label = label;
  }
  public String getBorderColor() {
    return borderColor;
  }
  public void setBorderColor(String borderColor) {
    this.borderColor = borderColor;
  }
  public String getBackgroundColor() {return backgroundColor;}
  public void setBackgroundColor(String backgroundColor) {this.backgroundColor = backgroundColor;}
  public boolean isPointRadius() {return pointRadius;}
  public void setPointRadius(boolean pointRadius) {this.pointRadius = pointRadius;}
  public String getPointColor() {
    return pointColor;
  }
  public void setPointColor(String pointColor) {
    this.pointColor = pointColor;
  }
  public String getPointStrokeColor() {
    return pointStrokeColor;
  }
  public void setPointStrokeColor(String pointStrokeColor) {
    this.pointStrokeColor = pointStrokeColor;
  }
  public String getPointHighlightFill() {
    return pointHighlightFill;
  }
  public void setPointHighlightFill(String pointHighlightFill) {
    this.pointHighlightFill = pointHighlightFill;
  }
  public String getPointHighlightStroke() {
    return pointHighlightStroke;
  }
  public void setPointHighlightStroke(String pointHighlightStroke) {
    this.pointHighlightStroke = pointHighlightStroke;
  }
  public int[] getData() {
    return data;
  }
  public void setData(int[] data) {
    this.data = data;
  }
  @Override
  public String toString() {
    return "Datasets{" + "label='" + label + '\'' +
            ", backgroundColor='" + backgroundColor + '\'' +
            ", borderColor='" + borderColor + '\'' +
            ", pointRadius=" + pointRadius +
            ", pointColor='" + pointColor + '\'' +
            ", pointStrokeColor='" + pointStrokeColor + '\'' +
            ", pointHighlightFill='" + pointHighlightFill + '\'' +
            ", pointHighlightStroke='" + pointHighlightStroke + '\'' +
            ", data=" + Arrays.toString(data) +
            '}';
  }
}
