package com.realsolutions.qualitydeskapi.service;

import com.realsolutions.qualitydeskapi.dao.StepRepository;
import com.realsolutions.qualitydeskapi.entity.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StepServiceImpl implements StepService {

    private final StepRepository stepRepository;

    @Autowired
    public StepServiceImpl(StepRepository stepRepository) {
        this.stepRepository = stepRepository;
    }

    @Override
    public List<Step> findAll() {
        return stepRepository.findAll();
    }

    @Override
    public Optional<Step> findById(int id) {
        return stepRepository.findById(id);
    }

    @Override
    public void update(Step step) {
        stepRepository.save(step);
    }

    @Override
    public void deleteById(int id) {
        stepRepository.deleteById(id);
    }

    @Override
    public List<Step> findByOrderId(int orderId) {
        return stepRepository.findByOrderId(orderId);
    }

    @Override
    public List<Step> findBySizeId(int sizeId) {
        return stepRepository.findBySizeId(sizeId);
    }

    @Override
    public List<Step> findByMeasurePointId(int measurePointId) {
        return stepRepository.findByMeasurePointId(measurePointId);
    }

    @Override
    public List<Step> findByOrderIdMeasurePointIdSizeId(int orderId, int sizeId, int measurePointId) {
        return stepRepository.findByOrderIdMeasurePointIdSizeId(orderId, sizeId, measurePointId);
    }

}
