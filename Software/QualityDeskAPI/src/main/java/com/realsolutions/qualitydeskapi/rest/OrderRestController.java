package com.realsolutions.qualitydeskapi.rest;

import com.realsolutions.qualitydeskapi.entity.Order;
import com.realsolutions.qualitydeskapi.entity.Size;
import com.realsolutions.qualitydeskapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/Order")
public class OrderRestController {

    private final OrderService orderService;

    @Autowired
    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/GetOrders")
    public HashMap<Object, Object> GetOrders() {

        HashMap<Object, Object> ordersMap = new HashMap<>();

        List<Order> orderList = orderService.findAll();

        ordersMap.put("Status", "Not Found");

        int counter = 1;

        for (Order order : orderList) {

            HashMap<Object, Object> orderMap = new HashMap<>();

            orderMap.put("Id", order.getId());
            orderMap.put("OrderNo", order.getOrderNo());
            orderMap.put("ModelName", order.getModelName());
            ordersMap.put(counter, orderMap);

            counter++;

            ordersMap.put("Status", "OK");
        }

        return ordersMap;

    }
}
