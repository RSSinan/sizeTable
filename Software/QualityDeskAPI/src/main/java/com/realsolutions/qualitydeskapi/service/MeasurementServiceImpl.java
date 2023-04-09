package com.realsolutions.qualitydeskapi.service;

import com.realsolutions.qualitydeskapi.dao.MeasurementRepository;
import com.realsolutions.qualitydeskapi.entity.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementServiceImpl(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @Override
    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    @Override
    public Optional<Measurement> findById(int id) {
        return measurementRepository.findById(id);
    }

    @Override
    public void update(Measurement measurement) {
        measurementRepository.save(measurement);
    }

    @Override
    public void deleteById(int id) {
        measurementRepository.deleteById(id);
    }

    @Override
    public List<Measurement> findByUserId(int userId) {
        return measurementRepository.findByUserId(userId);
    }

    @Override
    public List<Measurement> findByStepId(int stepId) {
        return measurementRepository.findByStepId(stepId);
    }
}
