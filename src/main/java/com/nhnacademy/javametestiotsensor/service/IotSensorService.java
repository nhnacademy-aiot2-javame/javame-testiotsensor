package com.nhnacademy.javametestiotsensor.service;


import com.nhnacademy.javametestiotsensor.dto.ChartData;
import com.nhnacademy.javametestiotsensor.dto.SensorResponse;
import com.nhnacademy.javametestiotsensor.dto.SensorStatistics;
import com.nhnacademy.javametestiotsensor.model.IotSensorData;
import com.nhnacademy.javametestiotsensor.repository.IotSensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IotSensorService {

    @Autowired
    private IotSensorRepository sensorRepository;

    // 모든 센서 데이터 조회
    public List<SensorResponse> getAllSensorData() {
        return sensorRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // ID로 센서 데이터 조회
    public Optional<SensorResponse> getSensorDataById(Long id) {
        return sensorRepository.findById(id)
                .map(this::convertToDto);
    }

    // 디바이스 ID로 센서 데이터 조회
    public List<SensorResponse> getSensorDataByDeviceId(String deviceId) {
        return sensorRepository.findByDeviceId(deviceId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 센서 타입으로 데이터 조회
    public List<SensorResponse> getSensorDataByType(String sensorType) {
        return sensorRepository.findBySensorType(sensorType).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 디바이스 ID와 센서 타입으로 데이터 조회
    public List<SensorResponse> getSensorDataByDeviceAndType(String deviceId, String sensorType) {
        return sensorRepository.findByDeviceIdAndSensorType(deviceId, sensorType).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 시간 범위로 데이터 조회
    public List<SensorResponse> getSensorDataByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        return sensorRepository.findByReceivedAtBetween(startTime, endTime).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 디바이스 ID와 시간 범위로 데이터 조회
    public List<SensorResponse> getSensorDataByDeviceAndTimeRange(
            String deviceId, LocalDateTime startTime, LocalDateTime endTime) {
        return sensorRepository.findByDeviceIdAndReceivedAtBetween(deviceId, startTime, endTime).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 센서 타입별 통계 조회
    public List<SensorStatistics> getSensorStatistics() {
        return sensorRepository.getSensorStatistics();
    }

    // 특정 센서 타입의 특정 시간 범위 내 통계 조회
    public SensorStatistics getSensorTypeStatistics(
            String sensorType, LocalDateTime startTime, LocalDateTime endTime) {
        return sensorRepository.getSensorTypeStatistics(sensorType, startTime, endTime);
    }

    // 모든 센서 타입 목록 조회
    public List<String> getAllSensorTypes() {
        return sensorRepository.findAllSensorTypes();
    }

    // 모든 디바이스 ID 목록 조회
    public List<String> getAllDeviceIds() {
        return sensorRepository.findAllDeviceIds();
    }

    // 특정 센서 타입의 최근 데이터로 차트 데이터 생성
    public ChartData getChartDataForSensorType(String sensorType) {
        List<IotSensorData> recentData = sensorRepository.findRecentBySensorType(sensorType);

        List<String> labels = recentData.stream()
                .map(data -> data.getReceivedAt().format(DateTimeFormatter.ofPattern("MM-dd HH:mm")))
                .collect(Collectors.toList());

        List<Object> values = recentData.stream()
                .map(IotSensorData::getSensorValue)
                .collect(Collectors.toList());

        return new ChartData(labels, values);
    }

    // 모든 센서 타입의 통계 데이터로 차트 데이터 생성 (파이 차트용)
    public ChartData getPieChartData() {
        List<SensorStatistics> statistics = sensorRepository.getSensorStatistics();

        List<String> labels = statistics.stream()
                .map(SensorStatistics::getSensorType)
                .collect(Collectors.toList());

        List<Object> values = statistics.stream()
                .map(SensorStatistics::getCount)
                .collect(Collectors.toList());

        return new ChartData(labels, values);
    }

    // Entity를 DTO로 변환
    private SensorResponse convertToDto(IotSensorData entity) {
        SensorResponse dto = new SensorResponse();
        dto.setDataId(entity.getDataId());
        dto.setDeviceId(entity.getDeviceId());
        dto.setSensorType(entity.getSensorType());
        dto.setSensorValue(entity.getSensorValue());
        dto.setUnit(entity.getUnit());
        dto.setReceivedAt(entity.getReceivedAt());
        return dto;
    }
}
