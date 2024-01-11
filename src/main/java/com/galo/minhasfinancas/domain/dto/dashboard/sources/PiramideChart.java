package com.galo.minhasfinancas.domain.dto.dashboard.sources;

import java.util.Arrays;
import java.util.List;

public class PiramideChart {
    private String[] labels;
    private List<PiramideDatasets> datasets;

    public PiramideChart() {}

    public String[] getLabels() {return labels;}
    public void setLabels(String[] labels) {this.labels = labels;}
    public List<PiramideDatasets> getDatasets() {return datasets;}
    public void setDatasets(List<PiramideDatasets> datasets) {this.datasets = datasets;}
    @Override
    public String toString() {
        return "PiramideChart{" + "labels=" + Arrays.toString(labels) +
                ", datasets=" + datasets +
                '}';
    }
}
