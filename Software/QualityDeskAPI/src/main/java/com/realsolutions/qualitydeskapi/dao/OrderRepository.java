package com.realsolutions.qualitydeskapi.dao;

import com.realsolutions.qualitydeskapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT * FROM Orders WHERE OrderNo = :orderNo", nativeQuery = true)
    public List<Order> findByOrderNo(@Param("orderNo") String orderNo);
}