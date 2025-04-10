package com.nhnacademy.javametestiotsensor.dto;

import jakarta.persistence.ConstructorResult;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class SensorStatistics {
    private String sensorType;
    private BigDecimal minValue;
    private BigDecimal maxValue;
    private Double avgValue;
    private String unit;
    private Long count;

    public SensorStatistics() {
    }
    public SensorStatistics(String sensorType, BigDecimal minValue, BigDecimal maxValue,
                            Double avgValue, String unit, Long count) {
        this.sensorType = sensorType;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.avgValue = avgValue;
        this.unit = unit;
        this.count = count;
    }


}