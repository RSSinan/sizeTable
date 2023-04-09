package com.realsolutions.qualitydeskapi.dao;

import com.realsolutions.qualitydeskapi.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

    @Query(value = "SELECT * FROM Measurements WHERE CreatedUserId = :userId", nativeQuery = true)
    public List<Measurement> findByUserId(@Param("userId") int userId);

    @Query(value = "SELECT * FROM Measurements WHERE StepId = :stepId", nativeQuery = true)
    public List<Measurement> findByStepId(@Param("stepId") int stepId);

}