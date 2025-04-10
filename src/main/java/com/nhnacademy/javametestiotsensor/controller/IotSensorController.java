package com.nhnacademy.javametestiotsensor.controller;


import com.nhnacademy.javametestiotsensor.dto.ChartData;
import com.nhnacademy.javametestiotsensor.dto.SensorResponse;
import com.nhnacademy.javametestiotsensor.dto.SensorStatistics;
import com.nhnacademy.javametestiotsensor.service.IotSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/sensors")
public class IotSensorController {

    @Autowired
    private IotSensorService sensorService;

    // 모든 센서 데이터 조회 API
    @GetMapping
    public ResponseEntity<List<SensorResponse>> getAllSensorData() {
        List<SensorResponse> sensorData = sensorService.getAllSensorData();
        return ResponseEntity.ok(sensorData);
    }

    // ID로 센서 데이터 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<SensorResponse> getSensorDataById(@PathVariable Long id) {
        return sensorService.getSensorDataById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 디바이스 ID로 센서 데이터 조회 API
    @GetMapping("/device/{deviceId}")
    public ResponseEntity<List<SensorResponse>> getSensorDataByDeviceId(@PathVariable String deviceId) {
        List<SensorResponse> sensorData = sensorService.getSensorDataByDeviceId(deviceId);
        return ResponseEntity.ok(sensorData);
    }

    // 센서 타입으로 데이터 조회 API
    @GetMapping("/type/{sensorType}")
    public ResponseEntity<List<SensorResponse>> getSensorDataByType(@PathVariable String sensorType) {
        List<SensorResponse> sensorData = sensorService.getSensorDataByType(sensorType);
        return ResponseEntity.ok(sensorData);
    }

    // 디바이스 ID와 센서 타입으로 데이터 조회 API
    @GetMapping("/device/{deviceId}/type/{sensorType}")
    public ResponseEntity<List<SensorResponse>> getSensorDataByDeviceAndType(
            @PathVariable String deviceId, @PathVariable String sensorType) {
        List<SensorResponse> sensorData = sensorService.getSensorDataByDeviceAndType(deviceId, sensorType);
        return ResponseEntity.ok(sensorData);
    }

    // 시간 범위로 데이터 조회 API
    @GetMapping("/timerange")
    public ResponseEntity<List<SensorResponse>> getSensorDataByTimeRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        List<SensorResponse> sensorData = sensorService.getSensorDataByTimeRange(startTime, endTime);
        return ResponseEntity.ok(sensorData);
    }

    // 디바이스 ID와 시간 범위로 데이터 조회 API
    @GetMapping("/device/{deviceId}/timerange")
    public ResponseEntity<List<SensorResponse>> getSensorDataByDeviceAndTimeRange(
            @PathVariable String deviceId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        List<SensorResponse> sensorData =
                sensorService.getSensorDataByDeviceAndTimeRange(deviceId, startTime, endTime);
        return ResponseEntity.ok(sensorData);
    }

    // 센서 타입별 통계 조회 API
    @GetMapping("/statistics")
    public ResponseEntity<List<SensorStatistics>> getSensorStatistics() {
        List<SensorStatistics> statistics = sensorService.getSensorStatistics();
        return ResponseEntity.ok(statistics);
    }

    // 특정 센서 타입의 특정 시간 범위 내 통계 조회 API
    @GetMapping("/type/{sensorType}/statistics")
    public ResponseEntity<SensorStatistics> getSensorTypeStatistics(
            @PathVariable String sensorType,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        SensorStatistics statistics =
                sensorService.getSensorTypeStatistics(sensorType, startTime, endTime);
        return ResponseEntity.ok(statistics);
    }

    // 특정 센서 타입의 차트 데이터 조회 API
    @GetMapping("/type/{sensorType}/chart")
    public ResponseEntity<ChartData> getChartDataForSensorType(@PathVariable String sensorType) {
        ChartData chartData = sensorService.getChartDataForSensorType(sensorType);
        return ResponseEntity.ok(chartData);
    }

    // 파이 차트 데이터 조회 API
    @GetMapping("/chart/pie")
    public ResponseEntity<ChartData> getPieChartData() {
        ChartData chartData = sensorService.getPieChartData();
        return ResponseEntity.ok(chartData);
    }

    // 모든 센서 타입 목록 조회 API
    @GetMapping("/types")
    public ResponseEntity<List<String>> getAllSensorTypes() {
        List<String> sensorTypes = sensorService.getAllSensorTypes();
        return ResponseEntity.ok(sensorTypes);
    }

    // 모든 디바이스 ID 목록 조회 API
    @GetMapping("/devices")
    public ResponseEntity<List<String>> getAllDeviceIds() {
        List<String> deviceIds = sensorService.getAllDeviceIds();
        return ResponseEntity.ok(deviceIds);
    }
}