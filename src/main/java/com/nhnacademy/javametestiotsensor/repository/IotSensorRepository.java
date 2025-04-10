package com.nhnacademy.javametestiotsensor.repository;


import com.nhnacademy.javametestiotsensor.dto.SensorStatistics;
import com.nhnacademy.javametestiotsensor.model.IotSensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IotSensorRepository extends JpaRepository<IotSensorData, Long> {

    // 특정 디바이스의 센서 데이터 조회
    List<IotSensorData> findByDeviceId(String deviceId);

    // 특정 센서 타입의 데이터 조회
    List<IotSensorData> findBySensorType(String sensorType);

    // 디바이스 ID와 센서 타입으로 조회
    List<IotSensorData> findByDeviceIdAndSensorType(String deviceId, String sensorType);

    // 특정 시간 범위 내 데이터 조회
    List<IotSensorData> findByReceivedAtBetween(LocalDateTime startTime, LocalDateTime endTime);

    // 특정 디바이스의 특정 시간 범위 내 데이터 조회
    List<IotSensorData> findByDeviceIdAndReceivedAtBetween(
            String deviceId, LocalDateTime startTime, LocalDateTime endTime);

    // 센서 타입별 통계 데이터 조회 (최소, 최대, 평균, 개수)
    @Query("SELECT new com.nhnacademy.javametestiotsensor.dto.SensorStatistics(s.sensorType, " +
            "MIN(s.sensorValue), MAX(s.sensorValue), AVG(s.sensorValue), " +
            "MAX(s.unit), COUNT(s)) " +
            "FROM IotSensorData s " +
            "GROUP BY s.sensorType")
    List<SensorStatistics> getSensorStatistics();

    // 특정 센서 타입의 특정 시간 범위 내 통계 데이터 조회
    @Query("SELECT new com.nhnacademy.javametestiotsensor.dto.SensorStatistics(s.sensorType, " +
            "MIN(s.sensorValue), MAX(s.sensorValue), AVG(s.sensorValue), " +
            "MAX(s.unit), COUNT(s)) " +
            "FROM IotSensorData s " +
            "WHERE s.sensorType = :sensorType AND " +
            "s.receivedAt BETWEEN :startTime AND :endTime " +
            "GROUP BY s.sensorType")
    SensorStatistics getSensorTypeStatistics(
            @Param("sensorType") String sensorType,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime);

    // 최근 데이터 조회 (차트용)
    @Query(value = "SELECT s FROM IotSensorData s WHERE s.sensorType = :sensorType ORDER BY s.receivedAt DESC LIMIT 10",
            nativeQuery = true)
    List<IotSensorData> findRecentBySensorType(@Param("sensorType") String sensorType);

    // 모든 센서 타입 목록 조회
    @Query("SELECT DISTINCT s.sensorType FROM IotSensorData s")
    List<String> findAllSensorTypes();

    // 모든 디바이스 ID 목록 조회
    @Query("SELECT DISTINCT s.deviceId FROM IotSensorData s")
    List<String> findAllDeviceIds();
}