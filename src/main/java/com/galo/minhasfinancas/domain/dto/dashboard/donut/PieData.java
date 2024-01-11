package com.galo.minhasfinancas.domain.dto.dashboard.donut;

public class PieData {

  private String[] labels;
  private Data[] datasets;

  public PieData() {
  }

  public PieData(String[] labels, Data[]  datasets) {
    this.datasets = datasets;
    this.labels = labels;
  }

  public Data[] getDatasets() {
    return datasets;
  }

  public void setDatasets(Data[] datasets) {
    this.datasets = datasets;
  }

  public String[] getLabels() {
    return labels;
  }

  public void setLabels(String[] labels) {
    this.labels = labels;
  }
}
