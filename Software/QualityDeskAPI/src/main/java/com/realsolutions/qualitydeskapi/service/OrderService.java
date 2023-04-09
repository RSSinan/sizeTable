package com.realsolutions.qualitydeskapi.service;


import com.realsolutions.qualitydeskapi.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    public List<Order> findAll();

    public Optional<Order> findById(int id);

    public void update(Order order);

    public void deleteById(int id);

    public List<Order> findByOrderNo(String orderNo);
}
