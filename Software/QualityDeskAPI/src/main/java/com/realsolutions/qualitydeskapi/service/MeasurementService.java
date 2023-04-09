package com.realsolutions.qualitydeskapi.service;


import com.realsolutions.qualitydeskapi.entity.Measurement;

import java.util.List;
import java.util.Optional;

public interface MeasurementService {

    public List<Measurement> findAll();

    public Optional<Measurement> findById(int id);

    public void update(Measurement measurement);

    public void deleteById(int id);

    public List<Measurement> findByUserId(int userId);

    public List<Measurement> findByStepId(int stepId);
}
