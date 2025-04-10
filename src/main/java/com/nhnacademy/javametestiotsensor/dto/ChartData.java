package com.nhnacademy.javametestiotsensor.dto;


import java.util.List;

public class ChartData {
    private List<String> labels;
    private List<Object> values;

    public ChartData() {}

    public ChartData(List<String> labels, List<Object> values) {
        this.labels = labels;
        this.values = values;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }
}