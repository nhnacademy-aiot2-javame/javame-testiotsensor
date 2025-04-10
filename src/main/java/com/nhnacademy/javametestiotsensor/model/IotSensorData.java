package com.nhnacademy.javametestiotsensor.model;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "iot_sensor_data")
public class IotSensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dataId;

    private String deviceId;
    private String sensorType;
    private BigDecimal sensorValue;
    private String unit;
    private LocalDateTime receivedAt;
    private LocalDateTime createdAt;

    // 기본 생성자
    public IotSensorData() {}

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "IotSensorData [dataId=" + dataId + ", deviceId=" + deviceId + ", sensorType=" + sensorType
                + ", sensorValue=" + sensorValue + ", unit=" + unit + ", receivedAt=" + receivedAt + ", createdAt="
                + createdAt + "]";
    }
}