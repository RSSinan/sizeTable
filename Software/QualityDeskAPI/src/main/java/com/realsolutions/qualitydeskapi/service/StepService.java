package com.realsolutions.qualitydeskapi.service;

import com.realsolutions.qualitydeskapi.entity.Step;

import java.util.List;
import java.util.Optional;

public interface StepService {

    public List<Step> findAll();

    public Optional<Step> findById(int id);

    public void update(Step step);

    public void deleteById(int id);

    public List<Step> findByOrderId(int orderId);

    public List<Step> findBySizeId(int sizeId);

    public List<Step> findByMeasurePointId(int measurePointId);

    public List<Step> findByOrderIdMeasurePointIdSizeId(int orderId, int sizeId, int measurePointId);
}
