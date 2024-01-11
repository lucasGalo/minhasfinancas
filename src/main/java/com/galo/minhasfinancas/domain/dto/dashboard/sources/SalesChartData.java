package com.galo.minhasfinancas.domain.dto.dashboard.sources;

import java.util.Arrays;
import java.util.List;

public class SalesChartData {
    private String[] labels;
    private List<Datasets> datasets;

    public SalesChartData() {
    }

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    public List<Datasets> getDatasets() {
        return datasets;
    }

    public void setDatasets(List<Datasets> datasets) {
        this.datasets = datasets;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SalesChartData{");
        sb.append("labels=").append(labels == null ? "null" : Arrays.asList(labels).toString());
        sb.append(", datasets=").append(datasets == null ? "null" : datasets);
        sb.append('}');
        return sb.toString();
    }
}
