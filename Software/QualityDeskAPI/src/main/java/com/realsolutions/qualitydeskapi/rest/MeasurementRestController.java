package com.realsolutions.qualitydeskapi.rest;

import com.realsolutions.qualitydeskapi.entity.Measurement;
import com.realsolutions.qualitydeskapi.service.MeasurementService;
import com.realsolutions.qualitydeskapi.service.StepService;
import com.realsolutions.qualitydeskapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;

@RestController
@RequestMapping("/api/Measurement")
public class MeasurementRestController {

    private final MeasurementService measurementService;
    private final UserService userService;
    private final StepService stepService;

    @Autowired
    public MeasurementRestController(MeasurementService measurementService, UserService userService, StepService stepService) {
        this.measurementService = measurementService;
        this.userService = userService;
        this.stepService = stepService;
    }

    @PostMapping("/AddMeasurement")
    public HashMap<Object, Object> AddMeasurement(@RequestBody HashMap<Object, Object> measurementMap) {

        HashMap<Object, Object> objectHashMap = new HashMap<>();

        Measurement measurement = new Measurement();

        measurement.setId(0);
        measurement.setValue(Float.parseFloat(String.valueOf(measurementMap.get("Value"))));
        measurement.setDifferance(Float.parseFloat(String.valueOf(measurementMap.get("Differance"))));
        measurement.setCreatedDate(Instant.parse(String.valueOf(measurementMap.get("CreatedDate"))));
        measurement.setUser(userService.findById((int) measurementMap.get("CreatedUserId")).get());
        measurement.setStep((stepService.findById((int) measurementMap.get("StepId")).get()));

        measurementService.update(measurement);

        objectHashMap.put("Status", "OK");

        return objectHashMap;
    }
}
