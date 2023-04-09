package com.realsolutions.qualitydeskapi.service;

import com.realsolutions.qualitydeskapi.dao.MeasurePointRepository;
import com.realsolutions.qualitydeskapi.entity.MeasurePoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurePointServiceImpl implements MeasurePointService{

    private final MeasurePointRepository measurePointRepository;

    @Autowired
    public MeasurePointServiceImpl(MeasurePointRepository measurePointRepository) {
        this.measurePointRepository = measurePointRepository;
    }

    @Override
    public List<MeasurePoint> findAll() {
        return measurePointRepository.findAll();
    }

    @Override
    public Optional<MeasurePoint> findById(int id) {
        return measurePointRepository.findById(id);
    }

    @Override
    public void update(MeasurePoint measurePoint) {
        measurePointRepository.save(measurePoint);
    }

    @Override
    public void deleteById(int id) {
        measurePointRepository.deleteById(id);
    }

    @Override
    public List<MeasurePoint> findByName(String name) {
        return measurePointRepository.findByName(name);
    }
}
