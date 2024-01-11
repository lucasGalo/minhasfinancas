package com.galo.minhasfinancas.domain.dto.dashboard.sources;

import java.util.Arrays;
import java.util.List;

public class VisitorsChart {
    private String[] labels;
    private List<VisitorsDatasets> datasets;

    public VisitorsChart() {}
    public String[] getLabels() {
        return labels;
    }
    public void setLabels(String[] labels) {
        this.labels = labels;
    }
    public List<VisitorsDatasets> getDatasets() {return datasets;}
    public void setDatasets(List<VisitorsDatasets> datasets) {this.datasets = datasets;}
    @Override
    public String toString() {
        return "VisitorsChart{" + "labels=" + Arrays.toString(labels) +
                ", datasets=" + datasets +
                '}';
    }
}
