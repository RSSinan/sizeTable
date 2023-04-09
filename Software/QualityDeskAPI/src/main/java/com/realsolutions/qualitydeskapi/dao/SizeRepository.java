package com.realsolutions.qualitydeskapi.dao;

import com.realsolutions.qualitydeskapi.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SizeRepository extends JpaRepository<Size, Integer> {

    @Query(value = "SELECT * FROM Sizes WHERE Name = :name", nativeQuery = true)
    public List<Size> findByName(@Param("name") String name);
}