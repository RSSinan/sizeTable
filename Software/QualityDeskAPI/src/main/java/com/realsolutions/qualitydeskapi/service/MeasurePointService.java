package com.realsolutions.qualitydeskapi.service;

import com.realsolutions.qualitydeskapi.entity.MeasurePoint;

import java.util.List;
import java.util.Optional;

public interface MeasurePointService {

    public List<MeasurePoint> findAll();

    public Optional<MeasurePoint> findById(int id);

    public void update(MeasurePoint measurePoint);

    public void deleteById(int id);

    public List<MeasurePoint> findByName(String name);
}
