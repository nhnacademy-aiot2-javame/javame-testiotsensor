package com.nhnacademy.javametestiotsensor.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SensorResponse {
    private Long dataId;
    private String deviceId;
    private String sensorType;
    private BigDecimal sensorValue;
    private String unit;
    private LocalDateTime receivedAt;

    public SensorResponse() {}

    // Getters and Setters
    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public BigDecimal getSensorValue() {
        return sensorValue;
    }

    public void setSensorValue(BigDecimal sensorValue) {
        this.sensorValue = sensorValue;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(LocalDateTime receivedAt) {
        this.receivedAt = receivedAt;
    }
}