package com.realsolutions.qualitydeskapi.rest;

import com.realsolutions.qualitydeskapi.entity.Step;
import com.realsolutions.qualitydeskapi.service.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public HashMap<Object, Object> GetStepsByOrderId(@RequestBody HashMap<Object, Object> orderMap) {

        HashMap<Object, Object> stepsMap = new HashMap<>();

        List<Step> stepList = stepService.findByOrderId((int) orderMap.get("orderId"));

        if (!stepList.isEmpty()) {

            for (Step step : stepList) {

                HashMap<Object, Object> stepMap = new HashMap<>();

                stepMap.put("Value", step.getValue());
                stepMap.put("SizeName", step.getSize().getName());
                stepMap.put("MeasurePointName", step.getMeasurePoint().getName());

                stepsMap.put(step.getId(), stepMap);

            }

        } else {
            stepsMap.put(0, 0);
        }

        return stepsMap;

    }
}
