package com.realsolutions.qualitydeskapi.rest;

import com.realsolutions.qualitydeskapi.entity.MeasurePoint;
import com.realsolutions.qualitydeskapi.service.MeasurePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/MeasurePoint")
public class MeasurePointRestController {

    private final MeasurePointService measurePointService;

    @Autowired
    public MeasurePointRestController(MeasurePointService measurePointService) {
        this.measurePointService = measurePointService;
    }

    @GetMapping("/GetMeasurePoints")
    public HashMap<Object, Object> GetMeasurePoints() {

        HashMap<Object, Object> measurePointMaps = new HashMap<>();

        List<MeasurePoint> measurePointList = measurePointService.findAll();

        measurePointMaps.put("Status", "Not Found");

        int counter = 1;

        for (MeasurePoint measurePoint: measurePointList) {
            HashMap<Object, Object> measurePointMap = new HashMap<>();
            measurePointMap.put("Id", measurePoint.getId());
            measurePointMap.put("Name", measurePoint.getName());
            measurePointMaps.put(counter, measurePointMap);

            counter++;

            measurePointMaps.put("Status", "OK");

        }

        return measurePointMaps;
    }
}
