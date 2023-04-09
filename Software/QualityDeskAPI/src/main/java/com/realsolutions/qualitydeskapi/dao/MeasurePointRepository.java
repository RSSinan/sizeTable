package com.realsolutions.qualitydeskapi.dao;

import com.realsolutions.qualitydeskapi.entity.MeasurePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MeasurePointRepository extends JpaRepository<MeasurePoint, Integer> {

    @Query(value = "SELECT * FROM MeasurePoints  WHERE Name = :name", nativeQuery = true)
    public List<MeasurePoint> findByName(@Param("name") String name);
}