package com.realsolutions.qualitydeskapi.dao;

import com.realsolutions.qualitydeskapi.entity.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StepRepository extends JpaRepository<Step, Integer> {
    @Query(value = "SELECT * FROM Steps WHERE OrderId = :orderId", nativeQuery = true)
    public List<Step> findByOrderId(@Param("orderId") int orderId);

    @Query(value = "SELECT * FROM Steps WHERE SizeId = :sizeId", nativeQuery = true)
    public List<Step> findBySizeId(@Param("sizeId") int sizeId);

    @Query(value = "SELECT * FROM Steps WHERE MeasurePointId = :measurePointId", nativeQuery = true)
    public List<Step> findByMeasurePointId(@Param("measurePointId") int measurePointId);

    @Query(value = "SELECT * FROM Steps WHERE OrderId = :orderId and SizeId = :sizeId and MeasurePointId = :measurePointId ", nativeQuery = true)
    public List<Step> findByOrderIdMeasurePointIdSizeId(@Param("orderId") int orderId, @Param("sizeId") int sizeId, @Param("measurePointId") int measurePointId);
}