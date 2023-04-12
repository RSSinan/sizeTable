package com.realsolutions.qualitydeskapi.rest;

import com.realsolutions.qualitydeskapi.entity.Step;
import com.realsolutions.qualitydeskapi.service.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/Step")
public class StepRestController {

    private final StepService stepService;

    @Autowired
    public StepRestController(StepService stepService) {
        this.stepService = stepService;
    }

    @GetMapping("/GetStepsByOrderId")
    public HashMap<Object, Object> GetStepsByOrderId(@RequestParam(name = "orderId") int orderId) {

        HashMap<Object, Object> stepsMap = new HashMap<>();

        List<Step> stepList = stepService.findByOrderId(orderId);

        if (!stepList.isEmpty()) {

            stepsMap.put("Status", "OK");

            int counter = 1;

            for (Step step : stepList) {

                HashMap<Object, Object> stepMap = new HashMap<>();

                stepMap.put("Id", step.getId());
                stepMap.put("Value", step.getValue());
                stepMap.put("SizeId", step.getSize().getId());
                stepMap.put("OrderId", step.getOrder().getId());
                stepMap.put("MeasurePointId", step.getMeasurePoint().getId());
                stepsMap.put(counter, stepMap);
                counter++;

            }

        } else {
            stepsMap.put("Status", "Not Found");
        }

        return stepsMap;

    }
}
