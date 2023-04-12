package com.realsolutions.qualitydeskapi.rest;

import com.realsolutions.qualitydeskapi.entity.Size;
import com.realsolutions.qualitydeskapi.entity.User;
import com.realsolutions.qualitydeskapi.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/Size")
public class SizeRestController {

    private final SizeService sizeService;

    @Autowired
    public SizeRestController(SizeService sizeService) {
        this.sizeService = sizeService;
    }

    @GetMapping("/GetSizes")
    public HashMap<Object, Object> GetSizes() {

        HashMap<Object, Object> sizesMap = new HashMap<>();

        List<Size> sizeList = sizeService.findAll();

        sizesMap.put("Status", "Not Found");

        int counter = 1;

        for (Size size : sizeList) {

            HashMap<Object, Object> sizeMap = new HashMap<>();

            sizeMap.put("Id", size.getId());
            sizeMap.put("Name", size.getName());
            sizesMap.put(counter, sizeMap);

            counter++;

            sizesMap.put("Status", "OK");


        }

        return sizesMap;

    }
}
